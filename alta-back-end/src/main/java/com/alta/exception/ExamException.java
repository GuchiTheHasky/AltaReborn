package com.alta.exception;

public class ExamException extends RuntimeException {

    private static final String MESSAGE = "Exam with specified id {%d} not found.";

    public ExamException(int id) {
        super(String.format(MESSAGE, id));
    }
}
