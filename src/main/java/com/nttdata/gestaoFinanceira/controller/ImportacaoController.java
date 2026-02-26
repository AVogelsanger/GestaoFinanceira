package com.nttdata.gestaoFinanceira.controller;

import com.nttdata.gestaoFinanceira.importacao.ImportacaoClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/importacao")
@RequiredArgsConstructor
public class ImportacaoController {

    private final ImportacaoClienteService service;

    @PostMapping("/clientes")
    public ResponseEntity<String> importarClientes(@RequestParam("arquivo") MultipartFile arquivo) {

        service.importarClientes(arquivo);
        return ResponseEntity.ok("Importação realizada com sucesso");
    }
}

