package com.alta.exception;

public class TopicException extends RuntimeException {
    private static final String MESSAGE_USING_ID = "Topic with specified id {%d} not found. Check the request details.";
    private static final String MESSAGE_USING_TITLE = "Topic with specified title {%s} not found. Check the request details.";

    public TopicException(int id) {
        super(String.format(MESSAGE_USING_ID, id));
    }

    public TopicException(String title) {
        super(String.format(MESSAGE_USING_TITLE, title));
    }
}
