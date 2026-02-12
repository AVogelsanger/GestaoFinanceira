create table contas (
    id bigint not null auto_increment,
    tipoConta varchar(100) not null,
    numeroConta varchar(100) not null unique,
    agencia varchar(6) not null,
    saldoAtual varchar(100) not null,
    dataAbertura varchar(10) not null,
--    nome varchar(100) not null,
--    cpf varchar(11) not null,
--    email varchar(100) not null,
--    telefone varchar(15),
--    dataCriacao date,
--    status varchar(50) not null,
    primary key (id)
);
