package com.alta.dto;

import com.alta.entity.Task;
import lombok.Data;

import java.util.List;

@Data
public class StudentDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String studentClass;
    private String status;
    private List<Task> tasks;
}
