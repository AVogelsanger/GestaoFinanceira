package com.nttdata.gestaoFinanceira.investimento;

import java.math.BigDecimal;
import java.util.UUID;

public record DadosInvestimentoComCambio(
        UUID id,
        BigDecimal valorAplicado,
        BigDecimal cotacaoUSD
) {}
