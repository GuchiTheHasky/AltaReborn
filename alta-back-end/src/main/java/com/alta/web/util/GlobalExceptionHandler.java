package com.alta.web.util;

import com.alta.exception.*;
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

    @ExceptionHandler(StudentException.class)
    public ResponseEntity<Object> handleStudentException(StudentException ex) {
        int id = extractIdFromExceptionMessage(ex.getMessage());
        return ResponseEntity.badRequest().body("Student not found for ID: " + id);
    }

    @ExceptionHandler(ExamException.class)
    public ResponseEntity<Object> handleExamException(ExamException ex) {
        int id = extractIdFromExceptionMessage(ex.getMessage());
        return ResponseEntity.badRequest().body("Exam not found for ID: " + id);
    }

    @ExceptionHandler(TaskException.class)
    public ResponseEntity<Object> handleTaskException(TaskException ex) {
        int id = extractIdFromExceptionMessage(ex.getMessage());
        return ResponseEntity.badRequest().body("Task not found for ID: " + id);
    }

    private int extractIdFromExceptionMessage(String message) {
        int startIdx = message.indexOf("{") + 1;
        int endIdx = message.indexOf("}");
        String idString = message.substring(startIdx, endIdx);
        return Integer.parseInt(idString.trim());
    }

}

