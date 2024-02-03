package com.alta.dto;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private int id;
    private String imagePath;
    private String level;
    private String text;
    private String answer;
    private String title;
//    private int topicId;
//    private boolean isDeleted; // todo: delete this field
//    Set<StudentDto> students;

}
