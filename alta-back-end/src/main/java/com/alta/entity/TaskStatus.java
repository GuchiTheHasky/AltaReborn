package com.alta.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum TaskStatus {
    DELETED,
    ASSIGNED;

    private String status;

    public static TaskStatus fromString(String status) {
        for (TaskStatus ts : TaskStatus.values()) {
            if (ts.status.equalsIgnoreCase(status)) {
                return ts;
            }
        }
        throw new IllegalArgumentException("No constant with text " + status + " found");
    }
}
