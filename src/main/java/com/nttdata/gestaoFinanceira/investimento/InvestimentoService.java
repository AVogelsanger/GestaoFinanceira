package com.nttdata.gestaoFinanceira.investimento;

import com.nttdata.gestaoFinanceira.conta.Conta;
import com.nttdata.gestaoFinanceira.conta.ContaRepository;
import com.nttdata.gestaoFinanceira.conta.StatusConta;
import com.nttdata.gestaoFinanceira.infra.RecursoNaoEncontradoException;
import com.nttdata.gestaoFinanceira.infra.brasilapi.cep.BrasilApiClient;
import com.nttdata.gestaoFinanceira.produtoFinanceiro.ProdutoFinanceiro;
import com.nttdata.gestaoFinanceira.produtoFinanceiro.ProdutoFinanceiroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class InvestimentoService {

    private final InvestimentoRepository investimentoRepository;
    private final ContaRepository contaRepository;
    private final ProdutoFinanceiroRepository produtoRepository;
    private final BrasilApiClient brasilApiClient;

    @Transactional
    public Investimento criarInvestimento(DadosCadastroInvestimento dados) {

        Conta conta = contaRepository.findById(dados.contaId())
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Conta não encontrada"));

        if (conta.getStatus() == StatusConta.INATIVA) {
            throw new IllegalArgumentException("Conta está inativa");
        }

        if (dados.valorAplicado() == null ||
                dados.valorAplicado().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor do investimento deve ser maior que zero");
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
