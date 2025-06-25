package com.itau.desafio.investimentos.service;

import com.itau.desafio.investimentos.client.CotacaoClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class CotacaoServiceCircuitBreaker {

    private final CotacaoClient cotacaoClient;

    @CircuitBreaker(name = "cotacaoService", fallbackMethod = "fallbackUltimaCotacao")
    public BigDecimal getUltimaCotacaoComProtecao(Long ativoId) {
        return cotacaoClient.buscarUltimaCotacao(ativoId);
    }

    public BigDecimal fallbackUltimaCotacao(Long ativoId, Throwable t) {
        log.warn("[CotacaoServiceCircuitBreaker]Circuit Breaker ativado para o ativo {} : {}! Fallback acionado", t.getMessage());
        return BigDecimal.ZERO;
    }
}
