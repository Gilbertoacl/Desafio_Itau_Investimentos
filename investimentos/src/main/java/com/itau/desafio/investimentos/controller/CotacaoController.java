package com.itau.desafio.investimentos.controller;

import com.itau.desafio.investimentos.domain.Cotacao;
import com.itau.desafio.investimentos.repository.CotacaoRepository;
import com.itau.desafio.investimentos.service.CotacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cotacoes")
@RequiredArgsConstructor
public class CotacaoController {

    private final CotacaoService cotacaoService;
    private final CotacaoRepository cotacaoRepository;

    @PostMapping
    public ResponseEntity<Cotacao> registrarCotacao(@RequestBody Cotacao cotacao) {
        Cotacao cotacaoSalva = cotacaoService.registrarCotacao(cotacao);

        return ResponseEntity.ok(cotacaoSalva);
    }

    @GetMapping("/ativo/{ativoId}/ultima")
    public ResponseEntity<Cotacao> obterUltimaCotacao(@PathVariable Long ativoId) {
        return cotacaoRepository.findUltimaCotacaoByAtivoId(ativoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
