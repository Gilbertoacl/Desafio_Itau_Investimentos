package com.itau.desafio.investimentos.service;

import com.itau.desafio.investimentos.domain.Posicao;
import com.itau.desafio.investimentos.domain.Usuario;
import com.itau.desafio.investimentos.dto.PosicaoResponseDTO;
import com.itau.desafio.investimentos.repository.PosicaoRepository;
import com.itau.desafio.investimentos.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PosicaoService {

    private final PosicaoRepository posicaoRepository;
    private final UsuarioRepository usuarioRepository;

    public List<PosicaoResponseDTO> listarPosicoesPorUsuario (Long usuarioId){
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("[PosicaoService] Usuário não encontrado."));

        List<Posicao> posicoes = posicaoRepository.findAll().stream()
                .filter(p -> p.getUsuario().getId().equals(usuarioId))
                .collect(Collectors.toList());

        return posicoes.stream()
                .map(p -> PosicaoResponseDTO.builder()
                        .ativoId(p.getAtivo().getId())
                        .codigo(p.getAtivo().getCodigo())
                        .nome(p.getAtivo().getNome())
                        .quantidade(p.getQuantidade())
                        .precoMedio(p.getPrecoMedio())
                        .pl(p.getPl())
                        .build())
                .collect(Collectors.toList());
    }
}
