package com.nttdata.gestaoFinanceira.conta;

import com.nttdata.gestaoFinanceira.cliente.Cliente;
import com.nttdata.gestaoFinanceira.investimento.Investimento;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Table(name = "contas")
@Entity(name = "Conta")
@Getter
@Setter
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
    @Column(unique = true, nullable = false)
    private String agencia;
    @Column(name = "saldo_atual", unique = true, nullable = false)
    private BigDecimal saldoAtual;
    @Column(name = "data_abertura")
    private String dataAbertura;

//    @Embedded
//    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)@JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    @OneToMany(mappedBy = "conta", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Investimento> investimentos;

    public Conta(DadosCadastroConta dados) {
        this.tipoConta = dados.tipoConta();
        this.numeroConta = dados.numeroConta();
        this.agencia = dados.agencia();
        this.saldoAtual = dados.saldoAtual();
        this.dataAbertura = dados.dataAbertura();
        this.cliente = new Cliente(dados.cliente());
        this.investimentos = Collections.singletonList(new Investimento(dados.investimento()));
    }
}
