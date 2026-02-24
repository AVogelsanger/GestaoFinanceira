package com.nttdata.gestaoFinanceira.controller;

import com.nttdata.gestaoFinanceira.cliente.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoCliente> cadastrar(@RequestBody DadosCadastroCliente dados) {
        Cliente cliente = service.criarCliente(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(new DadosDetalhamentoCliente(cliente));
    }

//    @GetMapping
//    public ResponseEntity<List<DadosDetalhamentoCliente>> listar() {
//        List<DadosDetalhamentoCliente> lista = service.listarTodos()
//                .stream()
//                .map(DadosDetalhamentoCliente::new)
//                .toList();
//
//        return ResponseEntity.ok(lista);
//    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoCliente>> listar(@RequestParam(required = false) Status status, Pageable pageable) {
        Page<Cliente> page;

        if (status != null) {
            page = service.listarPorStatus(status, pageable);
        } else {
            page = service.listar(pageable);
        }
        Page<DadosDetalhamentoCliente> dtoPage = page.map(DadosDetalhamentoCliente::new);
        return ResponseEntity.ok(dtoPage);
    }



    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoCliente> buscarPorId(@PathVariable UUID id) {
        Cliente cliente = service.buscarPorId(id);
        return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoCliente> atualizar(@PathVariable UUID id, @RequestBody DadosAtualizacaoCliente dados) {
        Cliente clienteAtualizado = service.atualizar(id, dados);
        return ResponseEntity.ok(new DadosDetalhamentoCliente(clienteAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.inativar(id);
        return ResponseEntity.noContent().build();
    }

}
