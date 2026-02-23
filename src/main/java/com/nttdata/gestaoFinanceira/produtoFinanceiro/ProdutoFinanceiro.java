package com.nttdata.gestaoFinanceira.produtoFinanceiro;


import com.nttdata.gestaoFinanceira.investimento.Investimento;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity(name = "ProdutoFinanceiro")
@Table(name = "produtos_financeiros")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoFinanceiro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String tipo;

    @Column(precision = 5, scale = 2)
    private BigDecimal taxaRendimento;

    private Integer prazoMinimoDias;

    @OneToMany(mappedBy = "produto", fetch = FetchType.LAZY)
    private List<Investimento> investimentos;
}
