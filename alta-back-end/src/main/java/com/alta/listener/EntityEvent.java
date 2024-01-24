package com.alta.listener;

import org.springframework.context.ApplicationEvent;
import java.time.LocalDate;


public class EntityEvent extends ApplicationEvent {
    private EventType eventType;

    private LocalDate date = LocalDate.now();

    public EntityEvent(Object source, EventType eventType) {
        super(source);
        this.eventType = eventType;
    }

    @Override
    public String toString() {
        return "EntityEvent{" +
                "eventType=" + eventType +
                ", date=" + date +
                ", source=" + source +
                '}';
    }
}
