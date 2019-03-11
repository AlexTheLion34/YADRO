package com.service.exception;

import org.springframework.http.HttpStatus;

public class ApiError {

    private HttpStatus status;
    private String message;

    private ApiError() {
    }

    ApiError(HttpStatus status, Exception ex) {
        this();
        this.status = status;
        this.message = ex.getMessage();
    }

    ApiError(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
