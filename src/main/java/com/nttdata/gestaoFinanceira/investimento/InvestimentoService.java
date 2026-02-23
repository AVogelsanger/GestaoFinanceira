package com.nttdata.gestaoFinanceira.investimento;

import com.nttdata.gestaoFinanceira.conta.Conta;
import com.nttdata.gestaoFinanceira.conta.ContaRepository;
import com.nttdata.gestaoFinanceira.conta.StatusConta;
import com.nttdata.gestaoFinanceira.infra.RecursoNaoEncontradoException;
import com.nttdata.gestaoFinanceira.produtoFinanceiro.ProdutoFinanceiro;
import com.nttdata.gestaoFinanceira.produtoFinanceiro.ProdutoFinanceiroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InvestimentoService {

    private final InvestimentoRepository investimentoRepository;
    private final ContaRepository contaRepository;
    private final ProdutoFinanceiroRepository produtoRepository;

    @Transactional
    public Investimento criarInvestimento(DadosCadastroInvestimento dados) {

        Conta conta = contaRepository.findById(dados.contaId())
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Conta não encontrada"));

        if (conta.getStatus() == StatusConta.INATIVA) {
            throw new IllegalArgumentException("Conta está inativa");
        }

        ProdutoFinanceiro produto = produtoRepository.findById(dados.produtoId())
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Produto não encontrado"));

        // Debita o valor da conta
        conta.debitar(dados.valorAplicado());

        // Cria o investimento
        Investimento investimento = new Investimento(dados, conta, produto);

        return investimentoRepository.save(investimento);
    }
}
