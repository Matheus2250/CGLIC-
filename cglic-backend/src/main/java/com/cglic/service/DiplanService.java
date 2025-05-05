package com.sistema.api.service;

import com.sistema.api.dto.DiplanDTO;
import com.sistema.api.exception.ResourceNotFoundException;
import com.sistema.api.model.DiplanData;
import com.sistema.api.repository.DiplanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiplanService {

    @Autowired
    private DiplanRepository diplanRepository;

    /**
     * Salva um novo registro DIPLAN
     */
    public DiplanDTO saveDiplanData(DiplanDTO diplanDTO) {
        DiplanData diplanData = mapToEntity(diplanDTO);
        diplanData.setDataCriacao(LocalDateTime.now());
        diplanData.setUltimaAtualizacao(LocalDateTime.now());
        
        DiplanData savedData = diplanRepository.save(diplanData);
        return mapToDTO(savedData);
    }

    /**
     * Atualiza um registro DIPLAN existente
     */
    public DiplanDTO updateDiplanData(Long id, DiplanDTO diplanDTO) {
        DiplanData diplanData = diplanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro DIPLAN não encontrado com id: " + id));

        // Atualiza os campos
        diplanData.setAno(diplanDTO.getAno());
        diplanData.setUnidade(diplanDTO.getUnidade());
        diplanData.setTipoPrograma(diplanDTO.getTipoPrograma());
        diplanData.setCodigoPrograma(diplanDTO.getCodigoPrograma());
        diplanData.setNomePrograma(diplanDTO.getNomePrograma());
        diplanData.setValorPrevisto(diplanDTO.getValorPrevisto());
        diplanData.setValorRealizado(diplanDTO.getValorRealizado());
        diplanData.setStatus(diplanDTO.getStatus());
        diplanData.setObservacoes(diplanDTO.getObservacoes());
        diplanData.setUsuarioCriacao(diplanDTO.getUsuarioCriacao());
        diplanData.setUltimaAtualizacao(LocalDateTime.now());

        DiplanData updatedData = diplanRepository.save(diplanData);
        return mapToDTO(updatedData);
    }

    /**
     * Busca um registro DIPLAN por ID
     */
    public DiplanDTO getDiplanDataById(Long id) {
        DiplanData diplanData = diplanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro DIPLAN não encontrado com id: " + id));
        return mapToDTO(diplanData);
    }

    /**
     * Busca todos os registros DIPLAN (paginados)
     */
    public Page<DiplanDTO> getAllDiplanData(Pageable pageable) {
        Page<DiplanData> diplanPage = diplanRepository.findAll(pageable);
        return diplanPage.map(this::mapToDTO);
    }

    /**
     * Busca registros DIPLAN por ano
     */
    public List<DiplanDTO> getDiplanDataByAno(Integer ano) {
        List<DiplanData> diplanDataList = diplanRepository.findByAno(ano);
        return diplanDataList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca registros DIPLAN por unidade
     */
    public List<DiplanDTO> getDiplanDataByUnidade(String unidade) {
        List<DiplanData> diplanDataList = diplanRepository.findByUnidade(unidade);
        return diplanDataList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Exclui um registro DIPLAN
     */
    public void deleteDiplanData(Long id) {
        DiplanData diplanData = diplanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro DIPLAN não encontrado com id: " + id));
        diplanRepository.delete(diplanData);
    }

    /**
     * Converte entidade DiplanData para DiplanDTO
     */
    private DiplanDTO mapToDTO(DiplanData diplanData) {
        DiplanDTO diplanDTO = new DiplanDTO();
        diplanDTO.setId(diplanData.getId());
        diplanDTO.setAno(diplanData.getAno());
        diplanDTO.setUnidade(diplanData.getUnidade());
        diplanDTO.setTipoPrograma(diplanData.getTipoPrograma());
        diplanDTO.setCodigoPrograma(diplanData.getCodigoPrograma());
        diplanDTO.setNomePrograma(diplanData.getNomePrograma());
        diplanDTO.setValorPrevisto(diplanData.getValorPrevisto());
        diplanDTO.setValorRealizado(diplanData.getValorRealizado());
        diplanDTO.setStatus(diplanData.getStatus());
        diplanDTO.setObservacoes(diplanData.getObservacoes());
        diplanDTO.setUsuarioCriacao(diplanData.getUsuarioCriacao());
        diplanDTO.setDataCriacao(diplanData.getDataCriacao());
        diplanDTO.setUltimaAtualizacao(diplanData.getUltimaAtualizacao());
        return diplanDTO;
    }

    /**
     * Converte DiplanDTO para entidade DiplanData
     */
    private DiplanData mapToEntity(DiplanDTO diplanDTO) {
        DiplanData diplanData = new DiplanData();
        diplanData.setAno(diplanDTO.getAno());
        diplanData.setUnidade(diplanDTO.getUnidade());
        diplanData.setTipoPrograma(diplanDTO.getTipoPrograma());
        diplanData.setCodigoPrograma(diplanDTO.getCodigoPrograma());
        diplanData.setNomePrograma(diplanDTO.getNomePrograma());
        diplanData.setValorPrevisto(diplanDTO.getValorPrevisto());
        diplanData.setValorRealizado(diplanDTO.getValorRealizado());
        diplanData.setStatus(diplanDTO.getStatus());
        diplanData.setObservacoes(diplanDTO.getObservacoes());
        diplanData.setUsuarioCriacao(diplanDTO.getUsuarioCriacao());
        return diplanData;
    }
}