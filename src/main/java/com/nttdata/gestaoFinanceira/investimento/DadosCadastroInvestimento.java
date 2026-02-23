package com.nttdata.gestaoFinanceira.investimento;

import com.nttdata.gestaoFinanceira.conta.Conta;
import com.nttdata.gestaoFinanceira.produtoFinanceiro.ProdutoFinanceiro;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record DadosCadastroInvestimento(
        BigDecimal valorAplicado,
        LocalDateTime dataAplicacao,
        StatusInvestimento status,
        UUID contaId,
        UUID produtoId) {
}
