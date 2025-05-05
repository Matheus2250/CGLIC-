package com.sistema.api.controller;

import com.sistema.api.dto.DiplanDTO;
import com.sistema.api.service.DiplanService;
import com.sistema.api.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/diplan")
@CrossOrigin(origins = "*")
public class DiplanController {

    @Autowired
    private DiplanService diplanService;

    @Autowired
    private FileStorageService fileStorageService;

    /**
     * Endpoint para carregar uma planilha DIPLAN
     */
    @PostMapping("/upload")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<Map<String, Object>>> uploadDiplanFile(@RequestParam("file") MultipartFile file) {
        List<Map<String, Object>> data = fileStorageService.readExcelData(file);
        return ResponseEntity.ok(data);
    }

    /**
     * Endpoint para salvar um registro DIPLAN
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<DiplanDTO> saveDiplanData(@Valid @RequestBody DiplanDTO diplanDTO) {
        DiplanDTO savedData = diplanService.saveDiplanData(diplanDTO);
        return new ResponseEntity<>(savedData, HttpStatus.CREATED);
    }

    /**
     * Endpoint para atualizar um registro DIPLAN
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<DiplanDTO> updateDiplanData(@PathVariable Long id, @Valid @RequestBody DiplanDTO diplanDTO) {
        DiplanDTO updatedData = diplanService.updateDiplanData(id, diplanDTO);
        return ResponseEntity.ok(updatedData);
    }

    /**
     * Endpoint para buscar um registro DIPLAN por ID
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<DiplanDTO> getDiplanDataById(@PathVariable Long id) {
        DiplanDTO diplanData = diplanService.getDiplanDataById(id);
        return ResponseEntity.ok(diplanData);
    }

    /**
     * Endpoint para listar todos os registros DIPLAN (paginados)
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Page<DiplanDTO>> getAllDiplanData(Pageable pageable) {
        Page<DiplanDTO> diplanPage = diplanService.getAllDiplanData(pageable);
        return ResponseEntity.ok(diplanPage);
    }

    /**
     * Endpoint para buscar registros DIPLAN por ano
     */
    @GetMapping("/ano/{ano}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<DiplanDTO>> getDiplanDataByAno(@PathVariable Integer ano) {
        List<DiplanDTO> diplanDataList = diplanService.getDiplanDataByAno(ano);
        return ResponseEntity.ok(diplanDataList);
    }

    /**
     * Endpoint para buscar registros DIPLAN por unidade
     */
    @GetMapping("/unidade/{unidade}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<DiplanDTO>> getDiplanDataByUnidade(@PathVariable String unidade) {
        List<DiplanDTO> diplanDataList = diplanService.getDiplanDataByUnidade(unidade);
        return ResponseEntity.ok(diplanDataList);
    }

    /**
     * Endpoint para excluir um registro DIPLAN
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Void> deleteDiplanData(@PathVariable Long id) {
        diplanService.deleteDiplanData(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint para baixar registros DIPLAN como Excel
     */
    @GetMapping("/export")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Resource> exportToExcel(@RequestParam(required = false) Integer ano,
                                                @RequestParam(required = false) String unidade) {
        List<DiplanDTO> diplanData;
        
        if (ano != null) {
            diplanData = diplanService.getDiplanDataByAno(ano);
        } else if (unidade != null && !unidade.isEmpty()) {
            diplanData = diplanService.getDiplanDataByUnidade(unidade);
        } else {
            diplanData = diplanService.getAllDiplanData(Pageable.unpaged()).getContent();
        }
        
        // Preparar dados para Excel
        List<Map<String, Object>> excelData = new ArrayList<>();
        List<String> headers = List.of("ID", "Ano", "Unidade", "Tipo Programa", "Código", 
                "Nome Programa", "Valor Previsto", "Valor Realizado", "Status", "Observações");
        
        for (DiplanDTO dto : diplanData) {
            Map<String, Object> row = Map.of(
                "ID", dto.getId(),
                "Ano", dto.getAno(),
                "Unidade", dto.getUnidade(),
                "Tipo Programa", dto.getTipoPrograma(),
                "Código", dto.getCodigoPrograma(),
                "Nome Programa", dto.getNomePrograma(),
                "Valor Previsto", dto.getValorPrevisto(),
                "Valor Realizado", dto.getValorRealizado(),
                "Status", dto.getStatus(),
                "Observações", dto.getObservacoes() != null ? dto.getObservacoes() : ""
            );
            excelData.add(row);
        }
        
        ByteArrayInputStream excelFile = fileStorageService.generateExcel(excelData, headers);
        
        String filename = "diplan_export.xlsx";
        
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(new ByteArrayResource(excelFile.readAllBytes()));
    }
}