package com.alta.dto;

import lombok.Data;

@Data
public class TaskDto {
    private int taskId;
    private String description;
    private String topicTitle;
    private String answer;
    private String imagePath;
    private String level;
}
