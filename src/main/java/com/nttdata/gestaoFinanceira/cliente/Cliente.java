package com.nttdata.gestaoFinanceira.cliente;

import com.nttdata.gestaoFinanceira.conta.Conta;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

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
    private String dataCadastro;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Conta> contas;


    public Cliente(DadosCadastroCliente dados) {
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.dataCadastro = dados.dataCadastro();
        this.status = dados.status();
    }
}
