-- Criar tabela de usuários
CREATE TABLE usuarios (
    usuario_id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(100) NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Criar tabela de transações (gastos e ganhos)
CREATE TABLE transacoes (
    transacao_id SERIAL PRIMARY KEY,
    usuario_id INT REFERENCES usuarios(usuario_id),
    tipo_transacao VARCHAR(10) NOT NULL CHECK (tipo_transacao IN ('gasto', 'ganho')),
    descricao VARCHAR(255) NOT NULL,
    valor NUMERIC(10, 2) NOT NULL,
    data_transacao DATE NOT NULL,
    categoria VARCHAR(50),
    tipo_frequencia VARCHAR(10) NOT NULL CHECK (tipo_frequencia IN ('fixo', 'eventual')),
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Criar tabela de metas
CREATE TABLE metas (
    meta_id SERIAL PRIMARY KEY,
    usuario_id INT REFERENCES usuarios(usuario_id),
    descricao VARCHAR(255) NOT NULL,
    valor_objetivo NUMERIC(10, 2) NOT NULL,
    valor_atual NUMERIC(10, 2) DEFAULT 0,
    data_meta DATE NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Criar tabela de investimentos
CREATE TABLE investimentos (
    investimento_id SERIAL PRIMARY KEY,
    usuario_id INT REFERENCES usuarios(usuario_id),
    descricao VARCHAR(255) NOT NULL,
    valor_investido NUMERIC(10, 2) NOT NULL,
    data_investimento DATE NOT NULL,
    tipo VARCHAR(50),
    retorno_esperado NUMERIC(10, 2),
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);