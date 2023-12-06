package com.alta.dto;

import com.alta.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String studentClass;
    private String status;
    private List<Task> tasks;
}
