package com.nttdata.gestaoFinanceira.controller;

import com.nttdata.gestaoFinanceira.investimento.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/investimentos")
@RequiredArgsConstructor
public class InvestimentoController {

    private final InvestimentoService service;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoInvestimento> criar(@RequestBody DadosCadastroInvestimento dados) {
        Investimento investimento = service.criarInvestimento(dados);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new DadosDetalhamentoInvestimento(investimento));
    }
}
