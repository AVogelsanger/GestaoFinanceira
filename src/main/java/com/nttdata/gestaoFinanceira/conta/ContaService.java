package com.nttdata.gestaoFinanceira.conta;

import com.nttdata.gestaoFinanceira.cliente.Cliente;
import com.nttdata.gestaoFinanceira.cliente.ClienteRepository;
import com.nttdata.gestaoFinanceira.infra.RecursoNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContaService {

    private final ContaRepository contaRepository;
    private final ClienteRepository clienteRepository;

    @Transactional
    public Conta criarConta(DadosCadastroConta dados) {
        if (contaRepository.existsByNumeroConta(dados.numeroConta())) {
            throw new IllegalArgumentException("Número da conta já existe");
        }
        Cliente cliente = clienteRepository.findById(dados.clienteId())
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Cliente não encontrado"));

        Conta conta = new Conta(dados, cliente);
        return contaRepository.save(conta);
    }

//    @Transactional(readOnly = true)
//    public Page<Conta> listar(Pageable pageable) {
//        return contaRepository.findAll(pageable);
//    }

    @Transactional(readOnly = true)
    public Conta buscarPorId(UUID id) {
        return contaRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Conta não encontrada"));
    }

    @Transactional(readOnly = true)
    public Page<Conta> listarPorCliente(UUID clienteId, Pageable pageable) {
        if (!clienteRepository.existsById(clienteId)) {
            throw new RecursoNaoEncontradoException("Cliente não encontrado");
        }
        return contaRepository.findByClienteId(clienteId, pageable);
    }

    @Transactional
    public Conta atualizar(UUID id, DadosAtualizacaoConta dados) {
        Conta conta = contaRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Conta não encontrada"));

        conta.atualizarInformacoes(dados);
        return conta;
    }

    @Transactional
    public void inativar(UUID id) {
        Conta conta = contaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Conta não encontrada"));
        conta.inativar();
    }

    @Transactional(readOnly = true)
    public Page<Conta> listarPorStatus(StatusConta status, Pageable pageable) {
        return contaRepository.findByStatus(status, pageable);
    }

}

