package com.alta.dto;

import com.alta.entity.Task;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private int id;
    private String fullName;
    private String email;
    private String grade;
    private String comment;
    private Set<TaskDto> tasks = new HashSet<>();
}
