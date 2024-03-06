package com.alta.web.controller;

import com.alta.exception.ZnoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public ResponseEntity<Object> handleValidationExceptions(Exception ex) {
        String errorMessage = (ex instanceof MethodArgumentNotValidException) ?
                "Validation error: " + Objects.requireNonNull(((MethodArgumentNotValidException) ex).getBindingResult().getFieldError()).getDefaultMessage() :
                (ex instanceof ConstraintViolationException) ?
                        "Validation error: " + ex.getMessage() :
                        "Internal Server Error";
        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler(ZnoException.class)
    public ResponseEntity<Object> handleZnoException(ZnoException ex) {
        int id = extractIdFromExceptionMessage(ex.getMessage());
        return ResponseEntity.badRequest().body("ZNO not found for ID: " + id);
    }

    private int extractIdFromExceptionMessage(String message) {
        int startIdx = message.indexOf("{") + 1;
        int endIdx = message.indexOf("}");
        return Integer.parseInt(message.substring(startIdx, endIdx));
    }
}
