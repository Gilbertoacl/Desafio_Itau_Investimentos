package com.itau.desafio.investimentos.repository;

import com.itau.desafio.investimentos.domain.Ativo;
import com.itau.desafio.investimentos.domain.Posicao;
import com.itau.desafio.investimentos.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PosicaoRepository extends JpaRepository<Posicao, Long> {
    Optional<Posicao> findByUsuarioAndAtivo(Usuario usuario, Ativo ativo);

    Optional<Posicao> findByUsuarioIdAndAtivoId(Long usuarioId, Long ativoId);
}
