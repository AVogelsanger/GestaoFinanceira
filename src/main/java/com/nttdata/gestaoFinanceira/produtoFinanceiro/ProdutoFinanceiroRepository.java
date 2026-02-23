package com.nttdata.gestaoFinanceira.produtoFinanceiro;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ProdutoFinanceiroRepository extends JpaRepository<ProdutoFinanceiro, UUID> {
}
