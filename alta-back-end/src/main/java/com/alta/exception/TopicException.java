package com.alta.exception;

public class TopicException extends RuntimeException {
    private static final String MESSAGE_USING_TITLE = "Topic with specified title {%s} not found. Check the request details.";
    private static final String MESSAGE_TOPIC_NULL = "Couldn't find ant topics, cause title is null";

    public TopicException(){
        super(MESSAGE_TOPIC_NULL);
    }
    public TopicException(String title) {
        super(String.format(MESSAGE_USING_TITLE, title));
    }
}
