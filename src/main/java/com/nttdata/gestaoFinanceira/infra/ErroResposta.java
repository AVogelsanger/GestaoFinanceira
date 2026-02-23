package com.nttdata.gestaoFinanceira.infra;

import java.time.LocalDateTime;

public record ErroResposta(
        LocalDateTime timestamp,
        int status,
        String mensagem
) {
}
