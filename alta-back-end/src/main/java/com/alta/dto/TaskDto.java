package com.alta.dto;

import lombok.*;

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
