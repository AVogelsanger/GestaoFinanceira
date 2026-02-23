package com.nttdata.gestaoFinanceira.investimento;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InvestimentoRepository extends JpaRepository<Investimento, UUID> {
}
