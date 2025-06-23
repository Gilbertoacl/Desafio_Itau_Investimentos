package com.itau.desafio.investimentos.repository;

import com.itau.desafio.investimentos.domain.Operacao;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RelatorioRepository extends JpaRepository<Operacao, Long> {

    @Query("""
            SELECT SUM(o.corretagem)
            FROM Operacao o
            """)
    BigDecimal somarCorretagensTotais();

    @Query("""
            SELECT o.usuario.nome, SUM(o.quantidade * o.precoUnitario) as total
            FROM Operacao o 
            GROUP BY o.usuario.nome
            ORDER BY total DESC
            """)
    List<Object[]> top10UsuarioPorPosicao(Pageable pageable);

    @Query("""
            SELECT o.usuario.nome, SUM(o.corretagem) as total
            FROM Operacao o
            GROUP BY o.usuario.nome
            ORDER BY total DESC
            """)
    List<Object[]> top10UsuarioPorCorretagem(Pageable pageable);
}
