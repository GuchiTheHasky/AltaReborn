package com.alta.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "image_path")
    private String imagePath;

    private String level;
    private String text;

//    @Column(name = "texthtml")
//    private String textHtml;
    private String answer;
    private String title;
}

