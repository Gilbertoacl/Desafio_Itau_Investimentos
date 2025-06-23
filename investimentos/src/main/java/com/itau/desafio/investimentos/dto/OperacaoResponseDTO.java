package com.itau.desafio.investimentos.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class OperacaoResponseDTO {
    private Long id;
    private Long usuarioId;
    private String nomeUsuario;
    private Long ativoId;
    private String ativoCodigo;
    private Integer quantidade;
    private BigDecimal precoUnitario;
    private String tipoOperacao;
    private BigDecimal corretagem;
    private LocalDateTime dataHora;
}
