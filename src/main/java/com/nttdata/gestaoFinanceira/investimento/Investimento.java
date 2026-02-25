package com.nttdata.gestaoFinanceira.investimento;

import com.nttdata.gestaoFinanceira.conta.Conta;
import com.nttdata.gestaoFinanceira.produtoFinanceiro.ProdutoFinanceiro;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "Investimento")
@Table(name = "investimentos")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Investimento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal valorAplicado;

    @Column(nullable = false)
    private LocalDateTime dataAplicacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusInvestimento status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_id", nullable = false)
    private Conta conta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", nullable = false)
    private ProdutoFinanceiro produto;

    @Column(name = "cotacao_usd", nullable = false, precision = 10, scale = 4)
    private BigDecimal cotacaoUSD;

    @Column(name = "valor_usd", nullable = false, precision = 15, scale = 2)
    private BigDecimal valorUSD;

    public Investimento(DadosCadastroInvestimento dados,
                        Conta conta,
                        ProdutoFinanceiro produto,
                        BigDecimal cotacaoUSD,
                        BigDecimal valorUSD) {
        this.valorAplicado = dados.valorAplicado();
        this.dataAplicacao = dados.dataAplicacao();
        this.status = StatusInvestimento.ATIVO;
        this.conta = conta;
        this.produto = produto;
        this.cotacaoUSD = cotacaoUSD;
        this.valorUSD = valorUSD;
    }

    public void associarConta(Conta conta) {
        this.conta = conta;
    }

}
