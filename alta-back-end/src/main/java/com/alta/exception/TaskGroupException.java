package com.alta.exception;

public class TaskGroupException extends RuntimeException {

    private static final String MESSAGE = "Task group with specified id {%d} not found.";

    public TaskGroupException(int id) {
        super(String.format(MESSAGE, id));
    }
}
