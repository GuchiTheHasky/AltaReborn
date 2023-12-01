package com.alta.exception;

public class ZnoException extends RuntimeException {
    private static final String MESSAGE = "Zno with specified id {%d} not found. Check the request details.";

    public ZnoException(int id) {
        super(String.format(MESSAGE, id));
    }
}
