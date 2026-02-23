package com.nttdata.gestaoFinanceira.controller;


import com.nttdata.gestaoFinanceira.conta.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/contas")
@RequiredArgsConstructor
public class ContaController {

    private final ContaService service;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoConta> cadastrar(@RequestBody DadosCadastroConta dados){
        Conta conta = service.criarConta(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(new DadosDetalhamentoConta(conta));
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoConta>> listar(
            @RequestParam(required = false) UUID clienteId,
            @RequestParam(required = false) StatusConta status,
            Pageable pageable) {

        Page<Conta> page = null;

        if (clienteId != null) {
            page = service.listarPorCliente(clienteId, pageable);
        } else if (status != null) {
            page = service.listarPorStatus(status, pageable);
        } else {
            page = service.listarPorStatus(StatusConta.ATIVA, pageable);
        }

        return ResponseEntity.ok(page.map(DadosDetalhamentoConta::new));
    }


    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoConta> buscarPorId(@PathVariable UUID id) {
        Conta conta = service.buscarPorId(id);
        return ResponseEntity.ok(new DadosDetalhamentoConta(conta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoConta> atualizar(@PathVariable UUID id, @RequestBody DadosAtualizacaoConta dados) {
        Conta contaAtualizada = service.atualizar(id, dados);
        return ResponseEntity.ok(new DadosDetalhamentoConta(contaAtualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.inativar(id);
        return ResponseEntity.noContent().build();
    }

}
