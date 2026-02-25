package com.nttdata.gestaoFinanceira.infra.brasilapi.taxa;

import java.util.List;

public record CambioResponse(
        String moeda,
        String data,
        List<CotacaoItem> cotacoes
) {}