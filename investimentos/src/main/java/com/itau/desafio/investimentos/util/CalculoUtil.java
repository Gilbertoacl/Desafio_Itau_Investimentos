package com.itau.desafio.investimentos.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CalculoUtil {

    public static BigDecimal calcularPrecoMedio(List<BigDecimal> precos, List<Integer> quantidades) {
        if (precos == null || quantidades == null || precos.size() != quantidades.size() || precos.isEmpty()) {
            throw new IllegalArgumentException("[CalculoUtil] Listas Inválidas para o calculo do Preço médio");
        }

        BigDecimal totalInvestido = BigDecimal.ZERO;
        int totalQuantidade = 0;

        for (int i = 0; i < precos.size(); i++) {
            BigDecimal preco = precos.get(i);
            Integer quantidade = quantidades.get(i);

            if (preco == null || quantidade <= 0) {
                throw new IllegalArgumentException("[CalculoUtil] Valores invalidos para o calculo do preço medio.");
            }

            totalInvestido = totalInvestido.add(preco.multiply(BigDecimal.valueOf(quantidade)));
            totalQuantidade += quantidade;
        }

        return totalInvestido.divide(BigDecimal.valueOf(totalQuantidade), 6, RoundingMode.HALF_UP);
    }
}
