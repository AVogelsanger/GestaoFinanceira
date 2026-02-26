package com.nttdata.gestaoFinanceira.infra.brasilapi.cep;


import com.nttdata.gestaoFinanceira.infra.brasilapi.taxa.CambioResponse;
import com.nttdata.gestaoFinanceira.infra.brasilapi.taxa.CotacaoItem;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.List;

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
            throw new IllegalArgumentException("Erro ao consultar CEP: " + e.getMessage());
        }
    }

    public CotacaoItem buscarCambio(String moeda) {
        try {
            CambioResponse response = webClient.get()
                    .uri("/cambio/v1/cotacao/{moeda}/{data}", moeda,
                            LocalDate.now().minusDays(1).toString())
                    .retrieve()
                    .bodyToMono(CambioResponse.class)
                    .block();

            if (response == null || response.cotacoes() == null || response.cotacoes().isEmpty()) {
                throw new IllegalArgumentException("Cotação não encontrada para " + moeda);
            }

            return response.cotacoes().getFirst();
        } catch (Exception e) {
            throw new IllegalArgumentException("Erro ao consultar câmbio: " + e.getMessage());
        }
    }

}
