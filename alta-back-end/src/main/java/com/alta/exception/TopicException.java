package com.alta.exception;

public class TopicException extends RuntimeException {
    private static final String MESSAGE = "Topic with specified id {%d} not found. Check the request details.";

    public TopicException(int id) {
        super(String.format(MESSAGE, id));
    }

    public TopicException(String s) {
        super(s);
    }
}
