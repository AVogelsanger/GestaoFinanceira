create table contas (
id bigint not null auto_increment,
tipo_conta varchar(100) not null,
numero_conta varchar(100) not null unique,
agencia varchar(6) not null,
saldo_atual varchar(100) not null,
data_abertura varchar(10) not null,
nome varchar(100) not null,
cpf varchar(11) not null,
email varchar(100) not null,
telefone varchar(15),
data_criacao varchar(10),
status varchar(50) not null,

primary key (id)
);