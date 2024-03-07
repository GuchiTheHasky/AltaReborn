package com.alta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PageableException extends RuntimeException {

    private static final String MESSAGE = "Invalid pagination parameters: page=%d, size=%d";

    public PageableException(int page, int size) {
        super(String.format(MESSAGE, page, size));
    }
}

