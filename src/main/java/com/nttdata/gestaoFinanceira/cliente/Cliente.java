package com.nttdata.gestaoFinanceira.cliente;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    @Column(name = "data_criacao")
    private String dataCriacao;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Cliente(DadosCadastroCliente dados) {
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.dataCriacao = dados.dataCriacao();
        this.status = dados.status();
    }
}
