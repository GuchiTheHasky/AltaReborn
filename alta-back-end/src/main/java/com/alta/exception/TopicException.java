package com.alta.exception;

public class TopicException extends RuntimeException {
    private static final String MESSAGE_USING_TITLE = "Topic with specified title {%s} not found. Check the request details.";

    public TopicException(String title) {
        super(String.format(MESSAGE_USING_TITLE, title));
    }
}
