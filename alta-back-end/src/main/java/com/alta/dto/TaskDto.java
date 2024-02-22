package com.alta.dto;

import com.alta.entity.Student;
import com.alta.entity.TaskStatus;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TaskDto {
    private int id;
    private String imagePath;
    private String level;
    private String text; // delete it
    private String answer;
    private String title;

}
