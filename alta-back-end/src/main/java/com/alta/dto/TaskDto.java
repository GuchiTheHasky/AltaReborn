package com.alta.dto;

import lombok.Data;

@Data
public class TaskDto {
    private int id;
    private String imagePath;
    private String level;
    private String text;

//    private String textHtml;
    private String answer;
    private String title;
    private int topicId;

    private boolean isDeleted;
}
