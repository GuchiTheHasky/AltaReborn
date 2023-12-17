package com.alta.dto;

import lombok.Data;


@Data
public class TaskDto {
    private int id;
    private String description;
    private TopicDto topicDto;
    private String answer;
    private String pathToImage;
    private String level;
}
