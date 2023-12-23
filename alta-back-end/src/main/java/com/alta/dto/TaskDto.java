package com.alta.dto;

import com.alta.entity.Student;
import com.alta.entity.Topic;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class TaskDto {
    private int id;
    private int number;
    private String pathToImage;
    private String level;
    private String description;
    private String answer;
    private Topic topic;
    private Set<Student> students = new HashSet<>();
}
