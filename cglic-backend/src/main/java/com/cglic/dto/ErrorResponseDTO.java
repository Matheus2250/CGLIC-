package com.cglic.dto;

import java.util.Date;
import java.util.Map;

public class ErrorResponseDTO {
    private Date timestamp;
    private int status;
    private String message;
    private String path;
    private Map<String, String> errors;

    // Construtores
    public ErrorResponseDTO() {
        this.timestamp = new Date();
    }

    public ErrorResponseDTO(int status, String message, String path) {
        this.timestamp = new Date();
        this.status = status;
        this.message = message;
        this.path = path;
    }

    public ErrorResponseDTO(int status, String message, String path, Map<String, String> errors) {
        this.timestamp = new Date();
        this.status = status;
        this.message = message;
        this.path = path;
        this.errors = errors;
    }

    // Getters e Setters
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}