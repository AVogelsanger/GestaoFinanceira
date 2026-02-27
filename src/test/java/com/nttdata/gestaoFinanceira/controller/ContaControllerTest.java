package com.nttdata.gestaoFinanceira.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.gestaoFinanceira.cliente.Cliente;
import com.nttdata.gestaoFinanceira.cliente.Status;
import com.nttdata.gestaoFinanceira.conta.*;
import com.nttdata.gestaoFinanceira.infra.brasilapi.cliente.MockBankApiClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;;

@WebMvcTest(ContaController.class)
class ContaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContaService service;

    @MockBean
    private MockBankApiClient mockBankApiClient;

    @Autowired
    private ObjectMapper objectMapper;

    private Conta criarContaMock(UUID id) {

        Cliente cliente = Cliente.builder()
                .id(UUID.randomUUID())
                .nome("Cliente Teste")
                .cpf("12345678901")
                .dataCadastro(LocalDate.now())
                .status(Status.ATIVO)
                .build();

        return Conta.builder()
                .id(id)
                .tipoConta(TipoConta.CORRENTE)
                .numeroConta("12345")
                .agencia("0001")
                .saldoAtual(BigDecimal.valueOf(1000))
                .dataAbertura(LocalDate.now())
                .status(StatusConta.ATIVA)
                .cliente(cliente) // ✅ ESSENCIAL
                .build();
    }


    @Test
    void deveCadastrarConta() throws Exception {

        UUID id = UUID.randomUUID();
        UUID clienteId = UUID.randomUUID();

        DadosCadastroConta dados = new DadosCadastroConta(
                TipoConta.CORRENTE,
                "12345",
                "0001",
                BigDecimal.valueOf(1000),
                LocalDate.now(),
                clienteId
        );

        Conta conta = criarContaMock(id);

        Mockito.when(service.criarConta(Mockito.any()))
                .thenReturn(conta);

        mockMvc.perform(post("/contas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dados)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.numeroConta").value("12345"));
    }

    @Test
    void deveBuscarContaPorId() throws Exception {

        UUID id = UUID.randomUUID();
        Conta conta = criarContaMock(id);

        Mockito.when(service.buscarPorId(id))
                .thenReturn(conta);

        mockMvc.perform(get("/contas/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numeroConta").value("12345"));
    }

    @Test
    void deveDepositar() throws Exception {

        UUID id = UUID.randomUUID();
        Conta conta = criarContaMock(id);

        DadosMovimentacaoConta dados = new DadosMovimentacaoConta(BigDecimal.valueOf(500));

        Mockito.when(service.depositar(Mockito.eq(id), Mockito.any()))
                .thenReturn(conta);

        mockMvc.perform(patch("/contas/{id}/deposito", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dados)))
                .andExpect(status().isOk());
    }

    @Test
    void deveSacar() throws Exception {

        UUID id = UUID.randomUUID();
        Conta conta = criarContaMock(id);

        DadosMovimentacaoConta dados = new DadosMovimentacaoConta(BigDecimal.valueOf(200));

        Mockito.when(service.sacar(Mockito.eq(id), Mockito.any()))
                .thenReturn(conta);

        mockMvc.perform(patch("/contas/{id}/saque", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dados)))
                .andExpect(status().isOk());
    }

    @Test
    void deveListarContas() throws Exception {

        UUID id = UUID.randomUUID();
        Conta conta = criarContaMock(id);

        Mockito.when(service.listarPorStatus(Mockito.eq(StatusConta.ATIVA), Mockito.any()))
                .thenReturn(new PageImpl<>(List.of(conta)));

        mockMvc.perform(get("/contas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].numeroConta").value("12345"));
    }

    @Test
    void deveConsultarSaldoExterno() throws Exception {

        UUID id = UUID.randomUUID();
        Conta conta = criarContaMock(id);

        SaldoResponse saldoResponse =
                new SaldoResponse("12345", BigDecimal.valueOf(5000));

        Mockito.when(service.buscarPorId(id)).thenReturn(conta);
        Mockito.when(mockBankApiClient.buscarSaldo("12345"))
                .thenReturn(saldoResponse);

        mockMvc.perform(get("/contas/{id}/saldo-externo", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.saldo").value(5000));
    }

    @Test
    void deveDeletarConta() throws Exception {

        UUID id = UUID.randomUUID();

        mockMvc.perform(delete("/contas/{id}", id))
                .andExpect(status().isNoContent());

        Mockito.verify(service).inativar(id);
    }
}