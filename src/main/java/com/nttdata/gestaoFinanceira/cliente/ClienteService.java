package com.nttdata.gestaoFinanceira.cliente;

import com.nttdata.gestaoFinanceira.infra.RecursoNaoEncontradoException;
import com.nttdata.gestaoFinanceira.infra.brasilapi.cep.BrasilApiClient;
import com.nttdata.gestaoFinanceira.infra.brasilapi.cep.CepResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private final BrasilApiClient brasilApiClient;

    @Transactional
    public Cliente criarCliente(DadosCadastroCliente dados) {

        if (repository.existsByCpf(dados.cpf())) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }

        Cliente cliente = new Cliente(dados);

        if (cliente.getDataCadastro() == null) {
            cliente.setDataCadastro(LocalDate.now());
        }
        if (cliente.getStatus() == null) {
            cliente.setStatus(Status.ATIVO);
        }
        if (dados.cep() == null || dados.cep().isBlank()) {
            throw new IllegalArgumentException("CEP é obrigatório");
        }

        CepResponse endereco = brasilApiClient.buscarCep(dados.cep());

        if (endereco == null) {
            throw new IllegalArgumentException("CEP inválido");
        }

        cliente.setCidade(endereco.city());
        cliente.setEstado(endereco.state());
        cliente.setLogradouro(endereco.street());
        cliente.setBairro(endereco.neighborhood());

        return repository.save(cliente);
    }

//    @Transactional(readOnly = true)
//    public List<Cliente> listarTodos() {
//        return repository.findAll();
//    }

    @Transactional(readOnly = true)
    public Page<Cliente> listar(Pageable pageable) {
        return repository.findByStatus(Status.ATIVO, pageable);
    }

    @Transactional(readOnly = true)
    public Cliente buscarPorId(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado"));
    }

    @Transactional
    public Cliente atualizar(UUID id, DadosAtualizacaoCliente dados) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Cliente não encontrado"));

        if (dados.cep() != null && !dados.cep().isBlank()) {
            CepResponse endereco = brasilApiClient.buscarCep(dados.cep());

            if (endereco == null) {
                throw new IllegalArgumentException("CEP inválido");
            }

            cliente.setCep(dados.cep());
            cliente.setCidade(endereco.city());
            cliente.setEstado(endereco.state());
            cliente.setLogradouro(endereco.street());
            cliente.setBairro(endereco.neighborhood());
        }

        cliente.atualizarInformacoes(dados);
        return cliente;
    }

    @Transactional
    public void inativar(UUID id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Cliente não encontrado"));

        cliente.setStatus(Status.INATIVO);
    }

    @Transactional(readOnly = true)
    public Page<Cliente> listarPorStatus(Status status, Pageable pageable) {
        return repository.findByStatus(status, pageable);
    }

}
