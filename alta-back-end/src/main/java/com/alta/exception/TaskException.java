package com.alta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class TaskException extends RuntimeException {
    private static final String MESSAGE = "Task with specified id {%d} not found. Check the request details.";

    public TaskException(int id) {
        super(String.format(MESSAGE, id));
    }
}
