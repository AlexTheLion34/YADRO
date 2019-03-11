package com.service.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new ApiError(INTERNAL_SERVER_ERROR, error, ex));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }


    @ExceptionHandler(InterfaceNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFound(InterfaceNotFoundException ex) {
        ApiError apiError = new ApiError(NOT_FOUND, ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<Object> handleIOException(IOException ex) {
        ApiError apiError = new ApiError(INTERNAL_SERVER_ERROR, ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(InterruptedException.class)
    public ResponseEntity<Object> handleInterruptedException(InterruptedException ex) {
        ApiError apiError = new ApiError(INTERNAL_SERVER_ERROR, ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(VersionDoesNotMatchException.class)
    public ResponseEntity<Object> handleVersionDoesNotMatchException(VersionDoesNotMatchException ex) {
        ApiError apiError = new ApiError(BAD_REQUEST, ex);
        return buildResponseEntity(apiError);
    }
}
