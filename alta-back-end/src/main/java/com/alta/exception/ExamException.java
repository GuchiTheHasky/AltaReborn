package com.alta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ExamException extends RuntimeException {

    private static final String MESSAGE = "Exam with specified id {%d} not found.";

    public ExamException(int id) {
        super(String.format(MESSAGE, id));
    }
}
