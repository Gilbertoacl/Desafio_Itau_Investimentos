package com.itau.desafio.investimentos.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itau.desafio.investimentos.domain.Cotacao;
import com.itau.desafio.investimentos.service.CotacaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CotacaoConsumer {

    private final CotacaoService cotacaoService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "cotacoes", groupId = "investimentos-group")
    public void consumir(ConsumerRecord<String, String> record) {
        try {
            String payload = record.value();
            Cotacao cotacao = objectMapper.readValue(payload, Cotacao.class);
            cotacaoService.registrarCotacao(cotacao);
            log.info("[CotacaoConsumer]Cotacão consumida e salva com sucesso: {}", cotacao);
        } catch (Exception e) {
            log.error("[CotacaoConsumer] Erro ao processar a mensagem.");
        }
    }
}
