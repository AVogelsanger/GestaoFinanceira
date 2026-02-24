package com.nttdata.gestaoFinanceira.infra.brasilapi.cliente;

import com.nttdata.gestaoFinanceira.conta.SaldoResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class MockBankApiClient {

    private final WebClient webClient;

    public MockBankApiClient(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("http://localhost:3000")
                .build();
    }

    public SaldoResponse buscarSaldo(String numeroConta) {
        return webClient.get()
                .uri("/saldo/{numeroConta}", numeroConta)
                .retrieve()
                .bodyToMono(SaldoResponse.class)
                .block();
    }
}

