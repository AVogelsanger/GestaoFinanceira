package com.nttdata.gestaoFinanceira.infra.brasilapi.taxa;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public record CotacaoItem(

        @JsonProperty("paridade_compra")
        BigDecimal paridadeCompra,

        @JsonProperty("paridade_venda")
        BigDecimal paridadeVenda,

        @JsonProperty("cotacao_compra")
        BigDecimal cotacaoCompra,

        @JsonProperty("cotacao_venda")
        BigDecimal cotacaoVenda,

        @JsonProperty("data_hora_cotacao")
        String dataHoraCotacao,

        @JsonProperty("tipo_boletim")
        String tipoBoletim
) {}
