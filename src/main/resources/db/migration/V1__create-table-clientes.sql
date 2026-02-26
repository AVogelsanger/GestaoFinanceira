CREATE TABLE clientes (
    id UUID NOT NULL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    email VARCHAR(150),
    telefone VARCHAR(15),
    data_cadastro DATE NOT NULL,
    status VARCHAR(30)
);