package com.nttdata.gestaoFinanceira.cliente;

public record DadosCadastroCliente(
        String nome,
        String cpf,
        String email,
        String telefone,
        String dataCadastro,
        Status status) {
}
