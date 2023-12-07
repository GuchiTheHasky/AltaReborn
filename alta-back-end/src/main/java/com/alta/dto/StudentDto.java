package com.alta.dto;

import com.alta.entity.Task;
import lombok.Data;
import java.util.Set;

@Data
public class StudentDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String grade;
    private String status;
    private Set<Task> tasks;
}
