package com.nttdata.gestaoFinanceira.cliente;

import java.time.LocalDate;

public record DadosCadastroCliente(
        String nome,
        String cpf,
        String email,
        String telefone,
        String cep,
        LocalDate dataCadastro,
        Status status) {
}
