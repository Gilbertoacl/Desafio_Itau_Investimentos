package com.itau.desafio.investimentos.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PosicaoResponseDTO {

    private Long ativoId;
    private String codigo;
    private String nome;
    private Integer quantidade;
    private BigDecimal precoMedio;
    private BigDecimal pl;
}
