package com.itau.desafio.investimentos.controller;

import com.itau.desafio.investimentos.domain.Operacao;
import com.itau.desafio.investimentos.dto.OperacaoResponseDTO;
import com.itau.desafio.investimentos.service.OperacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/operacoes")
@RequiredArgsConstructor
public class OperacaoController {

    private final OperacaoService operacaoService;

    @PostMapping
    public ResponseEntity<OperacaoResponseDTO> registraOperacao(@RequestBody Operacao operacao) {
        Operacao operacaoRegistrada = operacaoService.registraOperacao(operacao);

        OperacaoResponseDTO dto = OperacaoResponseDTO.builder()
                .id(operacaoRegistrada.getId())
                .usuarioId(operacaoRegistrada.getUsuario().getId())
                .nomeUsuario(operacaoRegistrada.getUsuario().getNome())
                .ativoId(operacaoRegistrada.getAtivo().getId())
                .ativoCodigo(operacaoRegistrada.getAtivo().getCodigo())
                .quantidade(operacaoRegistrada.getQuantidade())
                .precoUnitario(operacaoRegistrada.getPrecoUnitario())
                .tipoOperacao(operacaoRegistrada.getTipoOperacao().name())
                .corretagem(operacaoRegistrada.getCorretagem())
                .dataHora(operacaoRegistrada.getDataHora())
                .build();

        return ResponseEntity.ok(dto);
    }

}
