package com.nttdata.gestaoFinanceira.conta;

import com.nttdata.gestaoFinanceira.cliente.Cliente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "contas")
@Entity(name = "Conta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Conta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_conta")
    private TipoConta tipoConta;
    @Column(name = "numero_conta")
    private String numeroConta;
    private String agencia;
    @Column(name = "saldo_atual")
    private String saldoAtual;
    @Column(name = "data_abertura")
    private String dataAbertura;

    @Embedded
    private Cliente cliente;

    public Conta(DadosCadastroConta dados) {
        this.tipoConta = dados.tipoConta();
        this.numeroConta = dados.numeroConta();
        this.agencia = dados.agencia();
        this.saldoAtual = dados.saldoAtual();
        this.dataAbertura = dados.dataAbertura();
        this.cliente = new Cliente(dados.cliente());
    }
}
