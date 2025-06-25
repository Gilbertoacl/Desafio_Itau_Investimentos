package com.itau.desafio.investimentos.controller;

import com.itau.desafio.investimentos.service.CotacaoServiceCircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/teste-circuit")
@RequiredArgsConstructor
public class TestCircuitBreakerController {

    private final CotacaoServiceCircuitBreaker cotServiceCircuitBreacker;

    @GetMapping("/{ativoId}")
    public ResponseEntity<BigDecimal> testeCircuitBreaker(@PathVariable Long ativoId){
        BigDecimal preco = cotServiceCircuitBreacker.getUltimaCotacaoComProtecao(ativoId);
        return ResponseEntity.ok(preco);
    }
}
