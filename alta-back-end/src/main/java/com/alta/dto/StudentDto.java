package com.alta.dto;

import lombok.Data;


@Data
public class StudentDto {
    private int id;
    private String firstName;
    private String lastName;
    private String grade;
    private String comment;
}
