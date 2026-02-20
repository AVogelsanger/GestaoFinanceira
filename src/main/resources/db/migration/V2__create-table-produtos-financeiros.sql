CREATE TABLE ProdutosFinanceiros (
    id_produto UUID PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    tipo VARCHAR(50),
    taxa_rendimento DECIMAL(5,2),
    prazo_minimo_dias INT
);