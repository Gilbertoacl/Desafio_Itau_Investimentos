package com.itau.desafio.investimentos.client;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CotacaoClient {
    public BigDecimal buscarUltimaCotacao(Long ativoId) {
        if (ativoId.equals(999L)) {
            throw new RuntimeException("Erro simulado no serviço de cotações.");
        }

        return new BigDecimal("32.45");
    }
}
