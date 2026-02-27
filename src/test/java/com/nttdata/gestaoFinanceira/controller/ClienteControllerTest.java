package com.nttdata.gestaoFinanceira.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.gestaoFinanceira.cliente.*;
import com.nttdata.gestaoFinanceira.exportacao.ExportacaoClienteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService service;

    @MockBean
    private ExportacaoClienteService exportacaoClienteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCadastrarCliente() throws Exception {

        UUID id = UUID.randomUUID();

        Cliente cliente = Cliente.builder()
                .id(id)
                .nome("João")
                .cpf("12345678901")
                .email("joao@email.com")
                .telefone("999999999")
                .cep("01001000")
                .cidade("São Paulo")
                .estado("SP")
                .logradouro("Rua Teste")
                .bairro("Centro")
                .dataCadastro(LocalDate.now())
                .status(Status.ATIVO)
                .build();

        DadosCadastroCliente dados = new DadosCadastroCliente(
                "João",
                "12345678901",
                "joao@email.com",
                "999999999",
                "01001000",
                LocalDate.now(),
                Status.ATIVO
        );

        Mockito.when(service.criarCliente(Mockito.any()))
                .thenReturn(cliente);

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dados)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("João"))
                .andExpect(jsonPath("$.cpf").value("12345678901"));
    }

    @Test
    void deveListarClientes() throws Exception {

        Cliente cliente = Cliente.builder()
                .id(UUID.randomUUID())
                .nome("Maria")
                .cpf("98765432100")
                .dataCadastro(LocalDate.now())
                .status(Status.ATIVO)
                .build();

        Mockito.when(service.listar(Mockito.any()))
                .thenReturn(new PageImpl<>(List.of(cliente)));

        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].nome").value("Maria"));
    }

    @Test
    void deveBuscarClientePorId() throws Exception {

        UUID id = UUID.randomUUID();

        Cliente cliente = Cliente.builder()
                .id(id)
                .nome("Carlos")
                .cpf("11122233344")
                .dataCadastro(LocalDate.now())
                .status(Status.ATIVO)
                .build();

        Mockito.when(service.buscarPorId(id))
                .thenReturn(cliente);

        mockMvc.perform(get("/clientes/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Carlos"));
    }

    @Test
    void deveDeletarCliente() throws Exception {

        UUID id = UUID.randomUUID();

        mockMvc.perform(delete("/clientes/{id}", id))
                .andExpect(status().isNoContent());

        Mockito.verify(service).inativar(id);
    }

    @Test
    void deveExportarClientes() throws Exception {

        byte[] mockArquivo = "arquivo-fake".getBytes();

        Mockito.when(exportacaoClienteService.exportarClientes())
                .thenReturn(mockArquivo);

        mockMvc.perform(get("/clientes/exportar"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Disposition", "attachment; filename=clientes.xlsx"));
    }
}