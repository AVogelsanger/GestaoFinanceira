package com.nttdata.gestaoFinanceira.conta;

import com.nttdata.gestaoFinanceira.cliente.Cliente;
import com.nttdata.gestaoFinanceira.cliente.DadosCadastroCliente;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosCadastroConta(
        TipoConta tipoConta,
        String numeroConta,
        String agencia,
        String saldoAtual,
        String dataAbertura,
        DadosCadastroCliente cliente) {
}
