package com.alta.dto;

import com.alta.entity.Student;
import com.alta.entity.TaskStatus;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private int id;
    private String imagePath;
    private String level;
    private String text; // delete it
    private String answer;
    private String title;
    private Set<StudentDto> students;

}
