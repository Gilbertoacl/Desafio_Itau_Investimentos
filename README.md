# 💼 Desafio Itaú - API de Investimentos

Este projeto é uma solução completa para o desafio técnico proposto pelo Itaú para a vaga de Engenheiro de Software Jr., implementado em **Java 21 com Spring Boot 3.5**, utilizando MySQL como banco de dados.

---

## 🧠 Descrição Geral

A aplicação simula uma API de investimentos com as seguintes funcionalidades:
- Registro de **operações de compra/venda** de ativos
- Atualização de **preço médio** de ativos
- Atualização da **posição** dos usuários com base em cotações
- Consumo de cotações por **Kafka**
- Exposição de **relatórios** via API REST
- Tratamento de falhas com **Resilience4j (Circuit Breaker e Fallback)**

---

## 🏗️ Tecnologias e Conceitos Usados

- Java 21
- Spring Boot 3.5.3
- Spring Data JPA
- MySQL
- Apache Kafka
- Spring Retry
- Resilience4j
- Swagger / OpenAPI (Springdoc)
- JUnit 5

---

## 📊 Entidades e Domínio

- **Usuario**: cliente da corretora
- **Ativo**: representa ações ou ativos negociados
- **Operacao**: compra ou venda realizada
- **Cotacao**: preço atual de um ativo
- **Posicao**: posição financeira de um usuário por ativo

---

## 📈 Endpoints principais

- `POST /operacoes`: registrar uma operação (compra ou venda)
- `GET /ativos/{id}/cotacao`: retornar última cotação do ativo
- `GET /usuarios/{id}/posicao`: retornar posição consolidada do cliente
- `GET /usuarios/{id}/preco-medio`: retorna preço médio por ativo
- `GET /relatorios/top-corretagem`: top 10 por corretagem
- `GET /relatorios/top-posicao`: top 10 por posição
- `GET /relatorios/ganhos-corretora`: total de corretagem da corretora

Documentação Swagger disponível em `/swagger-ui.html`.

---

## 🔐 Resiliência e Engenharia do Caos

- Fallback e Retry no serviço de cotação com **Resilience4j**
- CircuitBreaker protege a aplicação contra falhas no microserviço externo
- Log de eventos e estado do circuito ativado

---

## 🔁 Kafka e Worker Assíncrono

- Consumer Kafka (`CotacaoConsumer`) consome nova cotação e atualiza entidades
- Implementado com idempotência (não duplica cotações)
- Retentativas com Spring Retry

---

## 🧪 Testes

- Testes unitários com JUnit 5
- Cobertura do cálculo de preço médio e corretagem
- Tentativa de aplicar **PIT Mutation Testing** com `pitest-junit5-plugin`, porém houve incompatibilidade de repositório no ambiente local.

---

## 📦 SQL de índices propostos

```sql
CREATE INDEX idx_operacoes_usuario ON operacoes (usuario_id);
CREATE INDEX idx_operacoes_ativo ON operacoes (ativo_id);
CREATE INDEX idx_operacoes_data ON operacoes (data_hora);
```

---

## ⚙️ Execução local

### Requisitos:
- Java 21
- MySQL rodando com schema `dbDesafioItau`
- Kafka local (porta padrão 9092)

### Variáveis de ambiente:

Defina no seu sistema ou `.env`:
```
DB_HOST
DB_NAME
DB_USER
DB_PASSWORD
```

---

## 🚀 Como rodar

```bash
mvn clean install
mvn spring-boot:run
```

Kafka irá consumir o tópico `cotacoes` automaticamente se ativo.

---

## 🧠 Observações Finais

- Projeto segue boas práticas de Clean Architecture (divisão clara de camadas)
- Circuit Breaker implementado com Resilience4j
- Swagger ativo com todos endpoints expostos

---
