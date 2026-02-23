package com.nttdata.gestaoFinanceira.cliente;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nttdata.gestaoFinanceira.conta.Conta;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table(name = "clientes")
@Entity(name = "Cliente")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, length = 150)
    private String nome;
    @Column(nullable = false, length = 11, unique = true)
    private String cpf;
    private String email;
    private String telefone;
    @Column(name = "data_cadastro", nullable = false)
    private LocalDate dataCadastro;

    @Enumerated(EnumType.STRING)
    private Status status;

    @JsonManagedReference
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Conta> contas = new ArrayList<>();


    public Cliente(DadosCadastroCliente dados) {
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.dataCadastro = dados.dataCadastro();
        this.status = dados.status();
    }

    public void atualizarInformacoes(DadosAtualizacaoCliente dados) {

        if (dados.nome() != null) {
            this.nome = dados.nome();
        }

        if (dados.email() != null) {
            this.email = dados.email();
        }

        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }

        if (dados.status() != null) {
            this.status = dados.status();
        }
    }

}
