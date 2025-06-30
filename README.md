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

- `POST /api/operacoes`: registrar uma operação (compra ou venda).
- `POST /api/cotacoes`: registrar uma cotação.
- `GET /api/cotacoes/ativo/{1}/ultima`: retornar última cotação do ativo.
- `GET /api/posicoes/{id}`: retornar posição consolidada do cliente.
- `GET /api/usuarios/{id}/ativos/{id}/preco-medio`: retornar o preço médio por ativo para um usuário.
- `GET /api/relatorios/top-clientes-posicao`: Top 10 clientes com maiores posições.
- `GET /api/relatorios/top-clientes-corretagem`: Top 10 clientes que mais pagaram corretagem.
- `GET /api/relatorios/corretagens`: retorna o valor financeiro ganho pela corretora com as corretagens.

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
docker-compose up --build
```

Kafka irá consumir o tópico `cotacoes` automaticamente se ativo.

---

## 🧠 Observações Finais

- Projeto segue boas práticas de Clean Architecture (divisão clara de camadas)
- Circuit Breaker implementado com Resilience4j
- Swagger ativo com todos endpoints expostos

---
