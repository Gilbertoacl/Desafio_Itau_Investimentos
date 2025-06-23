package com.itau.desafio.investimentos.service;

import com.itau.desafio.investimentos.domain.Cotacao;
import com.itau.desafio.investimentos.domain.Posicao;
import com.itau.desafio.investimentos.repository.CotacaoRepository;
import com.itau.desafio.investimentos.repository.PosicaoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CotacaoService {

    private final CotacaoRepository cotacaoRepository;
    private final PosicaoRepository posicaoRepository;

    @Transactional
    public Cotacao registrarCotacao(Cotacao cotacao) {
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
}
