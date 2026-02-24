package com.nttdata.gestaoFinanceira.cliente;

import java.time.LocalDate;
import java.util.UUID;

public record DadosDetalhamentoCliente(
        UUID id,
        String nome,
        String cpf,
        String email,
        String telefone,
        String cep,
        String cidade,
        String estado,
        String logradouro,
        String bairro,
        LocalDate dataCadastro,
        Status status
) {
    public DadosDetalhamentoCliente(Cliente cliente) {
        this(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getCep(),
                cliente.getCidade(),
                cliente.getEstado(),
                cliente.getLogradouro(),
                cliente.getBairro(),
                cliente.getDataCadastro(),
                cliente.getStatus()
        );
    }
}
