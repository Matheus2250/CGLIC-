package com.sistema.api.service;

import com.sistema.api.exception.FileStorageException;
import com.sistema.api.exception.ResourceNotFoundException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    public FileStorageService(@Value("${file.upload-dir}") String uploadDir) {
        this.fileStorageLocation = Paths.get(uploadDir)
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Não foi possível criar o diretório para armazenar os arquivos.", ex);
        }
    }

    /**
     * Armazena um arquivo no sistema de arquivos
     */
    public String storeFile(MultipartFile file) {
        // Normaliza o nome do arquivo
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileName = "";

        try {
            // Verifica se o nome do arquivo contém caracteres inválidos
            if (originalFileName.contains("..")) {
                throw new FileStorageException("O nome do arquivo contém uma sequência de caminho inválida " + originalFileName);
            }

            // Gera um nome de arquivo único usando UUID
            String fileExtension = "";
            if (originalFileName.contains(".")) {
                fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            }
            fileName = UUID.randomUUID().toString() + fileExtension;

            // Copia o arquivo para o local de armazenamento de destino
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Não foi possível armazenar o arquivo " + originalFileName + ". Tente novamente!", ex);
        }
    }

    /**
     * Carrega um arquivo como um recurso
     */
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            
            if (resource.exists()) {
                return resource;
            } else {
                throw new ResourceNotFoundException("Arquivo não encontrado: " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new ResourceNotFoundException("Arquivo não encontrado: " + fileName, ex);
        }
    }

    /**
     * Lê os dados de um arquivo Excel
     * Retorna uma lista de mapas representando as linhas e colunas do Excel
     */
    public List<Map<String, Object>> readExcelData(MultipartFile file) {
        try (InputStream is = file.getInputStream();
             Workbook workbook = WorkbookFactory.create(is)) {
            
            Sheet sheet = workbook.getSheetAt(0); // Assume primeira planilha
            
            // Obter cabeçalhos da primeira linha
            Row headerRow = sheet.getRow(0);
            List<String> headers = new ArrayList<>();
            for (Cell cell : headerRow) {
                headers.add(cell.getStringCellValue());
            }
            
            // Ler dados
            List<Map<String, Object>> rows = new ArrayList<>();
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);
                Map<String, Object> rowData = new HashMap<>();
                
                for (int j = 0; j < headers.size(); j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        switch (cell.getCellType()) {
                            case STRING:
                                rowData.put(headers.get(j), cell.getStringCellValue());
                                break;
                            case NUMERIC:
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    rowData.put(headers.get(j), cell.getDateCellValue());
                                } else {
                                    rowData.put(headers.get(j), cell.getNumericCellValue());
                                }
                                break;
                            case BOOLEAN:
                                rowData.put(headers.get(j), cell.getBooleanCellValue());
                                break;
                            case FORMULA:
                                // Processa o resultado da fórmula
                                switch (cell.getCachedFormulaResultType()) {
                                    case STRING:
                                        rowData.put(headers.get(j), cell.getStringCellValue());
                                        break;
                                    case NUMERIC:
                                        rowData.put(headers.get(j), cell.getNumericCellValue());
                                        break;
                                    default:
                                        rowData.put(headers.get(j), "");
                                }
                                break;
                            default:
                                rowData.put(headers.get(j), "");
                        }
                    } else {
                        rowData.put(headers.get(j), "");
                    }
                }
                rows.add(rowData);
            }
            
            return rows;
        } catch (IOException e) {
            throw new FileStorageException("Falha ao processar o arquivo Excel", e);
        }
    }

    /**
     * Gera um arquivo Excel a partir de dados
     */
    public ByteArrayInputStream generateExcel(List<Map<String, Object>> data, List<String> headers) {
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            
            Sheet sheet = workbook.createSheet("Dados");
            
            // Criar cabeçalhos
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.size(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers.get(i));
            }
            
            // Preencher dados
            int rowNum = 1;
            for (Map<String, Object> rowData : data) {
                Row row = sheet.createRow(rowNum++);
                
                int colNum = 0;
                for (String header : headers) {
                    Cell cell = row.createCell(colNum++);
                    Object value = rowData.get(header);
                    
                    if (value != null) {
                        if (value instanceof String) {
                            cell.setCellValue((String) value);
                        } else if (value instanceof Double) {
                            cell.setCellValue((Double) value);
                        } else if (value instanceof Boolean) {
                            cell.setCellValue((Boolean) value);
                        } else if (value instanceof java.util.Date) {
                            cell.setCellValue((java.util.Date) value);
                        } else {
                            cell.setCellValue(value.toString());
                        }
                    }
                }
            }
            
            // Auto-dimensionar colunas
            for (int i = 0; i < headers.size(); i++) {
                sheet.autoSizeColumn(i);
            }
            
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new FileStorageException("Falha ao gerar o arquivo Excel", e);
        }
    }
}