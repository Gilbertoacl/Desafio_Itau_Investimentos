package com.itau.desafio.investimentos.service;

import com.itau.desafio.investimentos.domain.*;
import com.itau.desafio.investimentos.repository.CotacaoRepository;
import com.itau.desafio.investimentos.repository.OperacaoRepository;
import com.itau.desafio.investimentos.repository.PosicaoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class OperacaoService {

    private final OperacaoRepository operacaoRepository;
    private final PosicaoRepository posicaoRepository;
    private final CotacaoRepository cotacaoRepository;

    @Transactional
    public Operacao registraOperacao(Operacao operacao) {
        operacao.setDataHora(LocalDateTime.now());

        Operacao operacaoSalva = operacaoRepository.save(operacao);

        atualizarPosicao(operacaoSalva);

        return operacaoSalva;
    }

    private void atualizarPosicao(Operacao operacao) {
        Usuario usuario = operacao.getUsuario();
        Ativo ativo = operacao.getAtivo();

        Posicao posicao = posicaoRepository.findByUsuarioAndAtivo(usuario, ativo)
                .orElse(Posicao.builder()
                        .usuario(usuario)
                        .ativo(ativo)
                        .quantidade(0)
                        .precoMedio(BigDecimal.ZERO)
                        .pl(BigDecimal.ZERO)
                        .build());

        if (operacao.getTipoOperacao() == TipoOperacao.COMPRA) {
            atualizaCompra(posicao, operacao);
        } else {
            atualizaVenda(posicao, operacao);
        }

        cotacaoRepository.findTopByAtivoOrderByDataHoraDesc(ativo)
                .ifPresent(cotacao -> {
                    BigDecimal pl = cotacao.getPrecoUnitario()
                            .subtract(posicao.getPrecoMedio())
                            .multiply(BigDecimal.valueOf(posicao.getQuantidade()));
                    posicao.setPl(pl.setScale(2, RoundingMode.HALF_UP));
                });

        posicaoRepository.save(posicao);
    }

    private void atualizaCompra(Posicao posicao, Operacao operacao) {
        int novaQuantidade = posicao.getQuantidade() + operacao.getQuantidade();
        BigDecimal totalInvestidoAtual = posicao.getPrecoMedio().multiply(BigDecimal.valueOf(posicao.getQuantidade()));
        BigDecimal totalinvestidoNovo = operacao.getPrecoUnitario().multiply(BigDecimal.valueOf(operacao.getQuantidade()));
        BigDecimal precoMedio = totalInvestidoAtual.add(totalinvestidoNovo).divide(BigDecimal.valueOf(novaQuantidade), 6, RoundingMode.HALF_UP);

        posicao.setQuantidade(novaQuantidade);
        posicao.setPrecoMedio(precoMedio);
    }

    private void atualizaVenda(Posicao posicao, Operacao operacao) {
        int novaQuantidade = posicao.getQuantidade() - operacao.getQuantidade();

        if (novaQuantidade < 0 ) {
            throw new IllegalArgumentException("A venda excede a posição atual");
        }

        posicao.setQuantidade(novaQuantidade);
    }

}
