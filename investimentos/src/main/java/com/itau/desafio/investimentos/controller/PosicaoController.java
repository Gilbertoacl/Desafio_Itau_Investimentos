package com.itau.desafio.investimentos.controller;

import com.itau.desafio.investimentos.dto.PosicaoResponseDTO;
import com.itau.desafio.investimentos.service.PosicaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posicoes")
@RequiredArgsConstructor
public class PosicaoController {

    private final PosicaoService posicaoService;

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<PosicaoResponseDTO>> listarPosicoes(@PathVariable Long usuarioId) {
        List<PosicaoResponseDTO> posicoes = posicaoService.listarPosicoesPorUsuario(usuarioId);

        return ResponseEntity.ok(posicoes);
    }

}
