package com.itau.desafio.investimentos.repository;

import com.itau.desafio.investimentos.domain.Ativo;
import com.itau.desafio.investimentos.domain.Operacao;
import com.itau.desafio.investimentos.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OperacaoRepository extends JpaRepository<Operacao, Long> {
    List<Operacao> findByUsuarioAndAtivoAndDataHoraAfter(Usuario usuario,
                                                         Ativo ativo,
                                                         LocalDateTime dataMinima);
}
