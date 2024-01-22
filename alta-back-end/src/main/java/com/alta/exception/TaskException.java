package com.alta.exception;

public class TaskException extends RuntimeException {
    private static final String MESSAGE = "Task with specified id {%d} not found. Check the request details.";

    public TaskException(int id) {
        super(String.format(MESSAGE, id));
    }

    public TaskException(String title) {
        super(String.format(MESSAGE, title));
    }
}
