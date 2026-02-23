package com.nttdata.gestaoFinanceira.conta;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record DadosDetalhamentoConta(
        UUID id,
        TipoConta tipoConta,
        String numeroConta,
        String agencia,
        BigDecimal saldoAtual,
        LocalDate dataAbertura,
        UUID clienteId
) {
    public DadosDetalhamentoConta(Conta conta) {
        this(
                conta.getId(),
                conta.getTipoConta(),
                conta.getNumeroConta(),
                conta.getAgencia(),
                conta.getSaldoAtual(),
                conta.getDataAbertura(),
                conta.getCliente().getId()
        );
    }
}
