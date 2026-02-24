package com.nttdata.gestaoFinanceira.infra.brasilapi.cep;


import com.nttdata.gestaoFinanceira.infra.brasilapi.taxa.CambioResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class BrasilApiClient {

    private final WebClient webClient;

    public BrasilApiClient(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("https://brasilapi.com.br/api")
                .build();
    }

    public CepResponse buscarCep(String cep) {
        try {
            return webClient.get()
                    .uri("/cep/v1/{cep}", cep)
                    .retrieve()
                    .bodyToMono(CepResponse.class)
                    .block();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Erro ao consultar CEP: " + e.getMessage());
        }
    }

    public CambioResponse buscarCambio(String moeda) {
        try {
            return webClient.get()
                    .uri("/cambio/v1/{moeda}", moeda)
                    .retrieve()
                    .bodyToMono(CambioResponse.class)
                    .block();
        } catch (Exception e) {
            throw new IllegalArgumentException("Erro ao consultar câmbio");
        }
    }

}
