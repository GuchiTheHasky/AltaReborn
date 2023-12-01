package com.alta.exception;

public class NoZnoEntityException extends RuntimeException {
    private static final String MESSAGE = "Zno with specified id {%d} not found. Check the request details.";

    public NoZnoEntityException(int id) {
        super(String.format(MESSAGE, id));
    }
}
