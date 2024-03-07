package com.alta.web.util;

import com.alta.exception.TopicException;
import com.alta.exception.ZnoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public ResponseEntity<Object> handleValidationExceptions(Exception ex) {
        String errorMessage = "Validation error: " + ex.getMessage();
        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler(ZnoException.class)
    public ResponseEntity<Object> handleZnoException(ZnoException ex) {
        int id = extractIdFromExceptionMessage(ex.getMessage());
        return ResponseEntity.badRequest().body("ZNO not found for ID: " + id);
    }

    @ExceptionHandler(TopicException.class)
    public ResponseEntity<Object> handleTopicException(TopicException ex) {
        String errorMessage = ex.getMessage();
        return ResponseEntity.badRequest().body(errorMessage);
    }

    private int extractIdFromExceptionMessage(String message) {
        int startIdx = message.indexOf("{") + 1;
        int endIdx = message.indexOf("}");
        return Integer.parseInt(message.substring(startIdx, endIdx));
    }
}

