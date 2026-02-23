package com.nttdata.gestaoFinanceira.conta;

import com.nttdata.gestaoFinanceira.cliente.DadosCadastroCliente;
import com.nttdata.gestaoFinanceira.investimento.DadosCadastroInvestimento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record DadosCadastroConta(
        TipoConta tipoConta,
        String numeroConta,
        String agencia,
        BigDecimal saldoAtual,
        LocalDate dataAbertura,
        UUID clienteId) {
}
