package com.alta.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDto {
    private int id;
    private String imagePath;
    private String level;
    private String title;
}
