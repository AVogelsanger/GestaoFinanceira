CREATE TABLE Investimentos (
    id UUID PRIMARY KEY,
    valor_aplicado DECIMAL(15,2) NOT NULL,
    data_aplicacao TIMESTAMP NOT NULL,
    status VARCHAR(30) NOT NULL,
    conta_id UUID NOT NULL,
    produto_id UUID NOT NULL,
    CONSTRAINT fk_investimento_conta
        FOREIGN KEY (conta_id)
        REFERENCES contas(id),
    CONSTRAINT fk_investimento_produto
        FOREIGN KEY (produto_id)
        REFERENCES produtos financeiros(id)
);