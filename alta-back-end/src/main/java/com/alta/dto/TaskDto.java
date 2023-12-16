package com.alta.dto;

import com.alta.entity.Topic;
import lombok.Data;


@Data
public class TaskDto {
    private int id;
    private String description;
    private Topic topic;
    private String answer;
    private String imageUrl;
    private String level;
}
