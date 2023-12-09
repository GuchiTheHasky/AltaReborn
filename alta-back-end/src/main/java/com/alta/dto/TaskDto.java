package com.alta.dto;

import com.alta.entity.Student;
import com.alta.entity.Topic;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class TaskDto {
    private Integer id;
    private int number;
    private String imagePath;
    private String level;
    private String text;
    private String answer;
    private Topic topic;
    private Set<Student> students = new HashSet<>();
}
