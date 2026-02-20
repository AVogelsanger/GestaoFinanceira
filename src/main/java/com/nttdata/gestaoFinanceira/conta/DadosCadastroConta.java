package com.nttdata.gestaoFinanceira.conta;

import com.nttdata.gestaoFinanceira.cliente.DadosCadastroCliente;
import com.nttdata.gestaoFinanceira.investimento.DadosCadastroInvestimento;

import java.math.BigDecimal;

public record DadosCadastroConta(
        TipoConta tipoConta,
        String numeroConta,
        String agencia,
        BigDecimal saldoAtual,
        String dataAbertura,
        DadosCadastroCliente cliente,
        DadosCadastroInvestimento investimento) {
}
