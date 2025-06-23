package com.itau.desafio.investimentos.repository;

import com.itau.desafio.investimentos.domain.Ativo;
import com.itau.desafio.investimentos.domain.Cotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CotacaoRepository extends JpaRepository<Cotacao, Long> {
    Optional<Cotacao> findTopByAtivoOrderByDataHoraDesc(Ativo ativo);

    @Query("""
            SELECT c FROM Cotacao c
            WHERE c.ativo.id = :ativoId
            ORDER BY c.dataHora DESC
            LIMIT 1
            """)
    Optional<Cotacao> findUltimaCotacaoByAtivoId(@Param("ativoId")Long ativoId);
}
