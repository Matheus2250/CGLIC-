package com.cglic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DipliDTO {
    private Long id;
    
    @NotNull(message = "O ano é obrigatório")
    private Integer ano;
    
    @NotBlank(message = "A unidade é obrigatória")
    private String unidade;
    
    @NotBlank(message = "O tipo de programa é obrigatório")
    private String tipoPrograma;
    
    @NotBlank(message = "O código do programa é obrigatório")
    private String codigoPrograma;
    
    @NotBlank(message = "O nome do programa é obrigatório")
    private String nomePrograma;
    
    @NotNull(message = "A data de início é obrigatória")
    @PastOrPresent(message = "A data de início deve ser no passado ou presente")
    private LocalDate dataInicio;
    
    private LocalDate dataConclusao;
    
    @NotNull(message = "O valor previsto é obrigatório")
    @Positive(message = "O valor previsto deve ser positivo")
    private BigDecimal valorPrevisto;
    
    private BigDecimal valorExecutado;
    
    private Integer percentualExecucao;
    
    private String status;
    
    private String responsavel;
    
    private String observacoes;
    
    private String usuarioCriacao;
    
    private LocalDateTime dataCriacao;
    
    private LocalDateTime ultimaAtualizacao;

    // Construtores
    public DipliDTO() {
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getTipoPrograma() {
        return tipoPrograma;
    }

    public void setTipoPrograma(String tipoPrograma) {
        this.tipoPrograma = tipoPrograma;
    }

    public String getCodigoPrograma() {
        return codigoPrograma;
    }

    public void setCodigoPrograma(String codigoPrograma) {
        this.codigoPrograma = codigoPrograma;
    }

    public String getNomePrograma() {
        return nomePrograma;
    }

    public void setNomePrograma(String nomePrograma) {
        this.nomePrograma = nomePrograma;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDate dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public BigDecimal getValorPrevisto() {
        return valorPrevisto;
    }

    public void setValorPrevisto(BigDecimal valorPrevisto) {
        this.valorPrevisto = valorPrevisto;
    }

    public BigDecimal getValorExecutado() {
        return valorExecutado;
    }

    public void setValorExecutado(BigDecimal valorExecutado) {
        this.valorExecutado = valorExecutado;
    }

    public Integer getPercentualExecucao() {
        return percentualExecucao;
    }

    public void setPercentualExecucao(Integer percentualExecucao) {
        this.percentualExecucao = percentualExecucao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getUsuarioCriacao() {
        return usuarioCriacao;
    }

    public void setUsuarioCriacao(String usuarioCriacao) {
        this.usuarioCriacao = usuarioCriacao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(LocalDateTime ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }
}