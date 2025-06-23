package com.itau.desafio.investimentos.controller;

import com.itau.desafio.investimentos.repository.PosicaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final PosicaoRepository posicaoRepository;

    @GetMapping("/{usuarioId}/ativos/{ativoId}/preco-medio")
    public ResponseEntity<BigDecimal> precoMedio(@PathVariable Long usuarioId,
                                                 @PathVariable Long ativoId){
        return posicaoRepository.findByUsuarioIdAndAtivoId(usuarioId, ativoId)
                .map(posicao -> ResponseEntity.ok(posicao.getPrecoMedio()))
                .orElse(ResponseEntity.notFound().build());
    }
}
