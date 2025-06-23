package com.itau.desafio.investimentos.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CalculoUtilTeste {

    @Test
    void deveCalcularPrecoMedioCorretamente() {
        List<BigDecimal> precos = List.of(new BigDecimal("10.00"), new BigDecimal("20.00"));
        List<Integer> quantidades = List.of(1,2);

        BigDecimal resultado = CalculoUtil.calcularPrecoMedio(precos, quantidades);

        assertEquals(new BigDecimal("16.666667"), resultado);
    }

    @Test
    void devemLancarExcecaoSeListarForemDeTamanhosDiferentes() {
        List<BigDecimal> precos = List.of(new BigDecimal("10.00"));
        List<Integer> quantidades = List.of(1,2);

        assertThrows(IllegalArgumentException.class, () -> CalculoUtil.calcularPrecoMedio(precos, quantidades));
    }

    @Test
    void deveLancarExcessaoSeQuantidadeForZero() {
        List<BigDecimal> precos = List.of(new BigDecimal("10.00"));
        List<Integer> quantidades = List.of(0);

        assertThrows(IllegalArgumentException.class, () -> CalculoUtil.calcularPrecoMedio(precos, quantidades));
    }
}
