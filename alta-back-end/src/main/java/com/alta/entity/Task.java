package com.alta.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLRestriction;

import java.util.Objects;
import java.util.Set;
import java.util.HashSet;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "task")
//@SQLRestriction("status = DELETED")
@ToString(exclude = {"tasks_groups", "topic"})
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "level")
    private String level;

    @Column(name = "text")
    private String text;

    @Column(name = "answer")
    private String answer;

    @Column(name = "title")
    private String title;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @JsonIgnore
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    @ManyToOne
    private Topic topic;

//    @ManyToMany(mappedBy = "tasks")
//    @JsonIgnoreProperties("tasks")
//    private Set<Student> students = new HashSet<>();

    @ManyToMany(mappedBy = "tasks")
    @JsonIgnoreProperties("tasks")
    private Set<TasksGroup> tasks_groups = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(imagePath, task.imagePath)
                && Objects.equals(level, task.level) && Objects.equals(text, task.text)
                && Objects.equals(answer, task.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imagePath, level, text, answer);
    }
}
