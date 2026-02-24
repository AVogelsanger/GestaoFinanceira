package com.nttdata.gestaoFinanceira.cliente;

public record DadosAtualizacaoCliente(
        String nome,
        String email,
        String telefone,
        Status status,
        String cep
) {
}
