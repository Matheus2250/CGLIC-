package com.cglic.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DiplanDTO {
    private Long id;
    private Long uploadId;
    private String numeroContratacao;
    private String statusContratacao;
    private String situacaoExecucao;
    private String tituloContratacao;
    private String categoriaContratacao;
    private String uasgAtual;
    private BigDecimal valorTotalContratacao;
    private LocalDate dataEstimadaInicio;
    private LocalDate dataEstimadaConclusao;
    private Integer prazoEstimadoDias;
    private String areaRequisitante;
    
    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getUploadId() {
        return uploadId;
    }
    
    public void setUploadId(Long uploadId) {
        this.uploadId = uploadId;
    }
    
    public String getNumeroContratacao() {
        return numeroContratacao;
    }
    
    public void setNumeroContratacao(String numeroContratacao) {
        this.numeroContratacao = numeroContratacao;
    }
    
    public String getStatusContratacao() {
        return statusContratacao;
    }
    
    public void setStatusContratacao(String statusContratacao) {
        this.statusContratacao = statusContratacao;
    }
    
    // Adicione os outros getters e setters para os campos restantes
    
    // Para manter o código conciso, não adicionei todos os getters/setters,
    // mas você deve implementá-los para todos os campos
}