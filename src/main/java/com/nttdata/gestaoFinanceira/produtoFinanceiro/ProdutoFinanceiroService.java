package com.nttdata.gestaoFinanceira.produtoFinanceiro;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProdutoFinanceiroService {

    private final ProdutoFinanceiroRepository repository;

    @Transactional
    public ProdutoFinanceiro criar(DadosCadastroProduto dados) {

        ProdutoFinanceiro produto = ProdutoFinanceiro.builder()
                .nome(dados.nome())
                .tipo(dados.tipo())
                .taxaRendimento(dados.taxaRendimento())
                .prazoMinimoDias(dados.prazoMinimoDias())
                .build();

        return repository.save(produto);
    }
}
