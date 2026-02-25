package com.nttdata.gestaoFinanceira.investimento;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record DadosDetalhamentoInvestimento(
        UUID id,
        BigDecimal valorAplicado,
        BigDecimal valorUSD,
        BigDecimal cotacaoUSD,
        LocalDateTime dataAplicacao,
        StatusInvestimento status,
        UUID contaId,
        UUID produtoId
) {
    public DadosDetalhamentoInvestimento(Investimento investimento) {
        this(
                investimento.getId(),
                investimento.getValorAplicado(),
                investimento.getValorUSD(),
                investimento.getCotacaoUSD(),
                investimento.getDataAplicacao(),
                investimento.getStatus(),
                investimento.getConta().getId(),
                investimento.getProduto().getId()
        );
    }
}
