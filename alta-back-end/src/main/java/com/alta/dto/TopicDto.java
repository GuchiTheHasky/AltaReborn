package com.alta.dto;

import com.alta.entity.Task;
import lombok.Data;

import java.util.List;

@Data
public class TopicDto {
    private int id;
    private String title;
    private List<Task> tasks;
}
