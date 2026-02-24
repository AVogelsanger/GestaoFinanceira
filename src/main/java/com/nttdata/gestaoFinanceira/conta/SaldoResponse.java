package com.nttdata.gestaoFinanceira.conta;

import java.math.BigDecimal;

public record SaldoResponse(
        String numeroConta,
        BigDecimal saldo
) {}
