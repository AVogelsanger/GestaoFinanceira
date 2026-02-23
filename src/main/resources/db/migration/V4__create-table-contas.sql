CREATE TABLE contas (
    id BINARY(16) NOT NULL PRIMARY KEY,
        cliente_id BINARY(16) NOT NULL,
        tipo_conta VARCHAR(50) NOT NULL,
        numero_conta VARCHAR(20) NOT NULL UNIQUE,
        agencia VARCHAR(20) NOT NULL,
        saldo_atual DECIMAL(15,2) NOT NULL,
        data_abertura DATE,

        CONSTRAINT fk_conta_cliente
            FOREIGN KEY (cliente_id)
            REFERENCES clientes(id)
);