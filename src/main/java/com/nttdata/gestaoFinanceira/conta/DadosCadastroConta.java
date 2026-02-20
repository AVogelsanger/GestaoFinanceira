package com.nttdata.gestaoFinanceira.conta;

import com.nttdata.gestaoFinanceira.cliente.DadosCadastroCliente;

public record DadosCadastroConta(
        TipoConta tipoConta,
        String numeroConta,
        String agencia,
        String saldoAtual,
        String dataAbertura,
        DadosCadastroCliente cliente) {
}
