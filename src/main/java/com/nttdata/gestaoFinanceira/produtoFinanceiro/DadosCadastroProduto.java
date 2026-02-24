package com.nttdata.gestaoFinanceira.produtoFinanceiro;

import java.math.BigDecimal;

public record DadosCadastroProduto(
        String nome,
        TipoProduto tipo,
        BigDecimal taxaRendimento,
        Integer prazoMinimoDias
) {
}
