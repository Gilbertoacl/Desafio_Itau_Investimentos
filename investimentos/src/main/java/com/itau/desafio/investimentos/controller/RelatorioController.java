package com.itau.desafio.investimentos.controller;


import com.itau.desafio.investimentos.repository.RelatorioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/relatorios")
@RequiredArgsConstructor
public class RelatorioController {

    private final RelatorioRepository relatorioRepository;

    @GetMapping("/corretagens")
    public ResponseEntity<BigDecimal> totalCorretagens() {
        return ResponseEntity.ok(relatorioRepository.somarCorretagensTotais());
    }

    @GetMapping("/top-clientes-posicao")
    public ResponseEntity<List<Map<String, Object>>> topClientesPosicao() {
        var lista = relatorioRepository.top10UsuarioPorPosicao(PageRequest.of(0,10));

        return ResponseEntity.ok(mapearObjeto(lista));
    }

    @GetMapping("/top-clientes-corretagem")
    public ResponseEntity<List<Map<String, Object>>> topClientesCorretagem() {
        var lista = relatorioRepository.top10UsuarioPorCorretagem(PageRequest.of(0,10));

        return ResponseEntity.ok(mapearObjeto(lista));
    }

    private List<Map<String, Object>> mapearObjeto(List<Object[]> dados) {
        return dados.stream().map(obj -> Map.of(
                "usuario", obj[0],
                "valor", obj[1]
        )).toList();
    }
}
