package com.alta.dto;

import lombok.Data;
import java.util.List;

@Data
public class TopicDto {
    private int id;
    private String name;
    private List<String> subtopics;
}
