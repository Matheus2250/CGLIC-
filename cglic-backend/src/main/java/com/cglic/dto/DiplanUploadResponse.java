package com.cglic.dto;

import java.time.LocalDateTime;

public class DiplanUploadResponse {
    private Long id;
    private String fileName;
    private String originalName;
    private LocalDateTime uploadDate;
    private Boolean isActive;
    private String uploadedBy;

    // Construtores
    public DiplanUploadResponse() {
    }

    public DiplanUploadResponse(Long id, String fileName, String originalName, 
                               LocalDateTime uploadDate, Boolean isActive, String uploadedBy) {
        this.id = id;
        this.fileName = fileName;
        this.originalName = originalName;
        this.uploadDate = uploadDate;
        this.isActive = isActive;
        this.uploadedBy = uploadedBy;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }
}