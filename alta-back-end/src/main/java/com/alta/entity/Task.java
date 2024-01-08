package com.alta.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;


@Data
@Entity
@Table(name = "task")
@ToString(exclude = {"topic", "isCompleted"})
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "image_path")
    private String imagePath;

    private String level;
    private String text;

    @Column(name = "texthtml")
    private String textHtml;
    private String answer;
    private String title;

    @Column(name = "is_completed")
    private boolean isCompleted;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    private Topic topic;
}

