package com.nttdata.gestaoFinanceira.conta;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.nttdata.gestaoFinanceira.cliente.Cliente;
import com.nttdata.gestaoFinanceira.investimento.Investimento;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Table(name = "contas")
@Entity(name = "Conta")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Conta {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_conta", nullable = false)
    private TipoConta tipoConta;
    @Column(name = "numero_conta", unique = true, nullable = false)
    private String numeroConta;
    @Column(nullable = false)
    private String agencia;
    @Column(name = "saldo_atual", nullable = false)
    private BigDecimal saldoAtual;
    @Column(name = "data_abertura")
    private LocalDate dataAbertura;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)@JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    @Builder.Default
    @OneToMany(mappedBy = "conta", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Investimento> investimentos = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusConta status;

    public Conta(DadosCadastroConta dados, Cliente cliente) {
        this.tipoConta = dados.tipoConta();
        this.numeroConta = dados.numeroConta();
        this.agencia = dados.agencia();
        this.saldoAtual = dados.saldoAtual();
        this.dataAbertura = dados.dataAbertura();
        this.cliente = cliente;
        this.status = StatusConta.ATIVA;
    }

    public void debitar(BigDecimal valor) {

        if (this.status == StatusConta.INATIVA) {
            throw new IllegalArgumentException("Conta está inativa");
        }

        if (this.saldoAtual.compareTo(valor) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }

        this.saldoAtual = this.saldoAtual.subtract(valor);
    }

    public void adicionarInvestimento(Investimento investimento) {
        investimento.associarConta(this);
        this.investimentos.add(investimento);
    }

    public void atualizarInformacoes(DadosAtualizacaoConta dados) {

        if (dados.tipoConta() != null) {
            this.tipoConta = dados.tipoConta();
        }

        if (dados.agencia() != null) {
            this.agencia = dados.agencia();
        }
    }

    public void inativar() {
        this.status = StatusConta.INATIVA;
    }

    public void depositar(BigDecimal valor) {

        if (this.status == StatusConta.INATIVA) {
            throw new IllegalArgumentException("Conta está inativa");
        }

        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor de depósito deve ser maior que zero");
        }

        this.saldoAtual = this.saldoAtual.add(valor);
    }

}
