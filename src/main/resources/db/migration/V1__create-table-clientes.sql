CREATE TABLE clientes (
-- postgree
--    id UUID PRIMARY KEY,
--    nome VARCHAR(150) NOT NULL,
--    cpf VARCHAR(11) UNIQUE NOT NULL,
--    email VARCHAR(150),
--    telefone VARCHAR (15),
--    data_cadastro VARCHAR(10) NOT NULL,
--    status VARCHAR (30)

    id BINARY(16) NOT NULL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    email VARCHAR(150),
    telefone VARCHAR(15),
    data_cadastro DATE NOT NULL,
    status VARCHAR(30)
);