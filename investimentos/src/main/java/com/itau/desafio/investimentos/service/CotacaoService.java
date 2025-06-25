package com.itau.desafio.investimentos.service;

import com.itau.desafio.investimentos.domain.Cotacao;
import com.itau.desafio.investimentos.domain.Posicao;
import com.itau.desafio.investimentos.repository.CotacaoRepository;
import com.itau.desafio.investimentos.repository.PosicaoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CotacaoService {

    private final CotacaoRepository cotacaoRepository;
    private final PosicaoRepository posicaoRepository;


    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000, multiplier = 2)
    )
    @Transactional
    public Cotacao registrarCotacao(Cotacao cotacao) {
        boolean existeCotacaoHoje = cotacaoRepository.contarCotacoesCadastradasNoDia(
                cotacao.getAtivo().getId(), cotacao.getPrecoUnitario()
        ) > 0 ;

        if (existeCotacaoHoje) {
            log.warn("[CotacaoService] Cotaçao duplicada registrada.");
            return null;
        }

        cotacao.setDataHora(LocalDateTime.now());
        Cotacao cotacaoSalva = cotacaoRepository.save(cotacao);

        atualizarPLDasPosicoes(cotacao);

        return cotacaoSalva;
    }

    private void atualizarPLDasPosicoes(Cotacao cotacao) {
        List<Posicao> posicoes = posicaoRepository.findAll().stream()
                .filter(p -> p.getAtivo().getId().equals(cotacao.getAtivo().getId()))
                .toList();

        for (Posicao posicao : posicoes) {
            BigDecimal pl = cotacao.getPrecoUnitario()
                    .subtract(posicao.getPrecoMedio())
                    .multiply(BigDecimal.valueOf(posicao.getQuantidade()))
                    .setScale(2, RoundingMode.HALF_UP);

            posicao.setPl(pl);
        }

        posicaoRepository.saveAll(posicoes);

    }

    public Cotacao fallbackRegistarCotacao(Exception e, Cotacao cotacao) {
        log.error("[fallbackRegistarCotacao] Falha ao salvar cotação. Salvando em fallback log ou DLQ.");

        return null;
    }
}
