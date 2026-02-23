CREATE TABLE produtos_financeiros (
    id BINARY(16) NOT NULL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    taxa_rendimento DECIMAL(5,2),
    prazo_minimo_dias INT
);