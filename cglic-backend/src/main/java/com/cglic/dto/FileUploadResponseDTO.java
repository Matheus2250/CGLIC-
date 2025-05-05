package com.cglic.dto;

import java.time.LocalDateTime;

public class FileUploadResponseDTO {
    private String fileName;
    private String originalFileName;
    private String fileDownloadUri;
    private String contentType;
    private long size;
    private LocalDateTime uploadTime;

    // Construtores
    public FileUploadResponseDTO() {
    }

    public FileUploadResponseDTO(String fileName, String originalFileName, String fileDownloadUri, 
                                String contentType, long size) {
        this.fileName = fileName;
        this.originalFileName = originalFileName;
        this.fileDownloadUri = fileDownloadUri;
        this.contentType = contentType;
        this.size = size;
        this.uploadTime = LocalDateTime.now();
    }

    // Getters e Setters
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }
}