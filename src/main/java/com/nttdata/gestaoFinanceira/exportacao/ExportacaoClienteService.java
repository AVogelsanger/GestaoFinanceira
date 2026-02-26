package com.nttdata.gestaoFinanceira.exportacao;

import com.nttdata.gestaoFinanceira.cliente.Cliente;
import com.nttdata.gestaoFinanceira.cliente.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExportacaoClienteService {

    private final ClienteRepository repository;

    public byte[] exportarClientes() {

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Clientes");

            // Cabeçalho
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Nome");
            header.createCell(1).setCellValue("CPF");
            header.createCell(2).setCellValue("Email");
            header.createCell(3).setCellValue("Telefone");
            header.createCell(4).setCellValue("CEP");
            header.createCell(5).setCellValue("Cidade");
            header.createCell(6).setCellValue("Estado");
            header.createCell(7).setCellValue("Logradouro");
            header.createCell(8).setCellValue("Bairro");
            header.createCell(9).setCellValue("Data Cadastro");
            header.createCell(10).setCellValue("Status");

            List<Cliente> clientes = repository.findAll();

            int rowIndex = 1;

            for (Cliente cliente : clientes) {

                Row row = sheet.createRow(rowIndex++);

                row.createCell(0).setCellValue(cliente.getNome());
                row.createCell(1).setCellValue(cliente.getCpf());
                row.createCell(2).setCellValue(cliente.getEmail());
                row.createCell(3).setCellValue(cliente.getTelefone());
                row.createCell(4).setCellValue(cliente.getCep());
                row.createCell(5).setCellValue(cliente.getCidade());
                row.createCell(6).setCellValue(cliente.getEstado());
                row.createCell(7).setCellValue(cliente.getLogradouro());
                row.createCell(8).setCellValue(cliente.getBairro());
                row.createCell(9).setCellValue(
                        cliente.getDataCadastro() != null ?
                                cliente.getDataCadastro().toString() : ""
                );
                row.createCell(10).setCellValue(
                        cliente.getStatus() != null ?
                                cliente.getStatus().name() : ""
                );
            }

            workbook.write(out);

            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar planilha: " + e.getMessage());
        }
    }
}
