package com.alta.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TaskWithAnswerDto extends TaskDto {
    private String answer;

    public TaskWithAnswerDto(int id, String imagePath, String level, String title, String answer) {
        super(id, imagePath, level, title);
        this.answer = answer;
    }
}
