CREATE TABLE investimentos (
    id BINARY(16) NOT NULL PRIMARY KEY,
    valor_aplicado DECIMAL(15,2) NOT NULL,
    data_aplicacao TIMESTAMP NOT NULL,
    status VARCHAR(30) NOT NULL,
    conta_id BINARY(16) NOT NULL,
    produto_id BINARY(16) NOT NULL,

    CONSTRAINT fk_investimento_conta
        FOREIGN KEY (conta_id)
        REFERENCES contas(id),

    CONSTRAINT fk_investimento_produto
        FOREIGN KEY (produto_id)
        REFERENCES produtos_financeiros(id)
);