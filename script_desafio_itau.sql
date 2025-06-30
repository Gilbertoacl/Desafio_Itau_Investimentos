CREATE DATABASE dbDesafioItau;
USE dbDesafioItau;

 -- Criação das Tabelas Usadas 
CREATE TABLE usuarios (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL, 
    email VARCHAR(100) UNIQUE NOT NULL, 
    corretagem_percentual DECIMAL(5,2) NOT NULL
);

CREATE TABLE ativos (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    codigo VARCHAR(10) NOT NULL,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE operacoes (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    usuario_id BIGINT NOT NULL,
    ativo_id BIGINT NOT NULL,
    quantidade INT NOT NULL,
    preco_unitario DECIMAL(5,2) NOT NULL, 
    tipo_operacao ENUM('COMPRA', 'VENDA') NOT NULL,
    corretagem DECIMAL(5,2) NOT NULL,
    data_hora DATETIME NOT NULL, 
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (ativo_id) REFERENCES ativos(id)
);

CREATE TABLE cotacoes (
	id BIGINT PRIMARY KEY AUTO_INCREMENT, 
    ativo_id BIGINT NOT NULL, 
    preco_unitario DECIMAL(5,2) NOT NULL,
    data_hora DATETIME NOT NULL, 
    FOREIGN KEY (ativo_id) REFERENCES ativos(id)
);

CREATE TABLE posicoes (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    usuario_id BIGINT NOT NULL,
    ativo_id BIGINT NOT NULL, 
    quantidade INT NOT NULL,
    preco_medio DECIMAL(5,2) NOT NULL, 
    pl DECIMAL(15,2) NOT NULL, 
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (ativo_id) REFERENCES ativos(id),
    UNIQUE(usuario_id, ativo_id)
);

-- Criar índices para a consulta de operações
CREATE INDEX idx_operacoes_usuario_ativo_data 
ON operacoes (usuario_id, ativo_id, data_hora);


