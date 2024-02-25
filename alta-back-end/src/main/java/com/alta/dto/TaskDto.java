package com.alta.dto;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private int id;
    private String imagePath;
    private String level;
    private String answer;
    private String title;
    private String status;
}
