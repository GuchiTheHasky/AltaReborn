package com.alta.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "task")
@ToString(exclude = {"topic", "exams"})
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // todo
    private int id;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "level")
    private String level;

    @Column(name = "answer")
    private String answer;

    @Column(name = "title")
    private String title;  // todo delete it, maybe

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "zno_id", referencedColumnName = "id")
    private Zno zno;

    @JsonIgnore
    @ManyToMany
    private List<Exam> exams;

}
