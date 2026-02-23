package com.nttdata.gestaoFinanceira.controller;

import com.nttdata.gestaoFinanceira.produtoFinanceiro.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoFinanceiroController {

    private final ProdutoFinanceiroService service;

    @PostMapping
    public ResponseEntity<ProdutoFinanceiro> cadastrar(@RequestBody DadosCadastroProduto dados) {
        ProdutoFinanceiro produto = service.criar(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }
}
