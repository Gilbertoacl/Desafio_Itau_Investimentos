package com.itau.desafio.investimentos.repository;

import com.itau.desafio.investimentos.domain.Ativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtivoRepository extends JpaRepository<Ativo, Long> {
    Ativo findByCodigo(String codigo);
}
