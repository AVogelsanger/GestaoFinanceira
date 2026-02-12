package com.nttdata.gestaoFinanceira.controller;


import com.nttdata.gestaoFinanceira.conta.Conta;
import com.nttdata.gestaoFinanceira.conta.ContaRepository;
import com.nttdata.gestaoFinanceira.conta.DadosCadastroConta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private ContaRepository repository;

    @PostMapping
    public void cadastrar(@RequestBody DadosCadastroConta dados){
        repository.save(new Conta(dados));
    }
}
