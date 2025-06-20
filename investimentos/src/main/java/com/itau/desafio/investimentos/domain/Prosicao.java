package com.itau.desafio.investimentos.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "posicoes", uniqueConstraints = { @UniqueConstraint(columnNames = {"usuario_id", "ativo_id"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prosicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ativo_id")
    private Ativo ativo;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(name = "preco_medio", nullable = false, precision = 15, scale = 2)
    private BigDecimal precoMedio;

    @Column(name = "pl", nullable = false, precision = 15, scale = 2)
    private BigDecimal pl;
}
