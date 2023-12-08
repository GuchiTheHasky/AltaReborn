package com.alta.dto;

import com.alta.entity.Task;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class TopicDto {
    private int id;
    private String name;
    private List<String> subtopics;
    private Set<Task> tasks = new HashSet<>();
}
