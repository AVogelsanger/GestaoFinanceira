package com.nttdata.gestaoFinanceira.importacao;

import com.nttdata.gestaoFinanceira.cliente.ClienteService;
import com.nttdata.gestaoFinanceira.cliente.DadosCadastroCliente;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


@Service
@RequiredArgsConstructor
public class ImportacaoClienteService {

    private final ClienteService clienteService;

    @Transactional
    public void importarClientes(MultipartFile arquivo) {

        try (Workbook workbook = new XSSFWorkbook(arquivo.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);

                if (row == null) continue;

                DataFormatter formatter = new DataFormatter();

                String nome = formatter.formatCellValue(row.getCell(0));
                String cpf = formatter.formatCellValue(row.getCell(1));
                String email = formatter.formatCellValue(row.getCell(2));
                String telefone = formatter.formatCellValue(row.getCell(3));
                String cep = formatter.formatCellValue(row.getCell(4));

                DadosCadastroCliente dados = new DadosCadastroCliente(
                        nome,
                        cpf,
                        email,
                        telefone,
                        cep,
                        null,
                        null
                );

                clienteService.criarCliente(dados);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao importar planilha: " + e.getMessage());
        }
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();

            case NUMERIC:
                return String.valueOf((long) cell.getNumericCellValue());

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());

            case FORMULA:
                return cell.getCellFormula();

            default:
                return "";
        }
    }

}

