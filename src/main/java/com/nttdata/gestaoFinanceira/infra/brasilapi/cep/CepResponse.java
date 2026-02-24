package com.nttdata.gestaoFinanceira.infra.brasilapi.cep;

public record CepResponse(
        String cep,
        String state,
        String city,
        String neighborhood,
        String street
) {}