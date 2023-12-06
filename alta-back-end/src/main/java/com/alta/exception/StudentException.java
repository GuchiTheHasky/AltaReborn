package com.alta.exception;

public class StudentException extends RuntimeException {
    private static final String MESSAGE = "Student with specified id {%d} not found. Check the request details.";

    public StudentException(int id) {
        super(String.format(MESSAGE, id));
    }
}
