CREATE TABLE Contas (
    id_conta UUID PRIMARY KEY,
    id_cliente UUID NOT NULL,
    tipo_conta VARCHAR (30),
    numero_conta VARCHAR(20) UNIQUE NOT NULL,
    agencia VARCHAR (20),
    saldo_atual VARCHAR (1000),
    data_abertura VARCHAR (10),
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente)
);