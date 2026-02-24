package com.nttdata.gestaoFinanceira.infra.brasilapi.taxa;

import java.math.BigDecimal;

public record CambioResponse(
        String currency,
        BigDecimal buy,
        BigDecimal sell,
        String date
) {}
