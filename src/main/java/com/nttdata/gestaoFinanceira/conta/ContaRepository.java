package com.nttdata.gestaoFinanceira.conta;

import com.nttdata.gestaoFinanceira.cliente.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContaRepository extends JpaRepository<Conta, UUID> {

    Optional<Conta> findByNumeroConta(String numeroConta);

    boolean existsByNumeroConta(String numeroConta);

    List<Conta> findByCliente(Cliente cliente);

    List<Conta> findByClienteId(UUID clienteId);

    Page<Conta> findByClienteId(UUID clienteId, Pageable pageable);

    Page<Conta> findByStatus(StatusConta status, Pageable pageable);

}
