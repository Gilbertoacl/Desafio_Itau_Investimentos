package com.itau.desafio.investimentos.repository;

import com.itau.desafio.investimentos.domain.Ativo;
import com.itau.desafio.investimentos.domain.Cotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CotacaoRepository extends JpaRepository<Cotacao, Long> {
    Optional<Cotacao> findTopByAtivoOrderByDataHoraDesc(Ativo ativo);
}
