package com.alta.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
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
@SQLRestriction("is_completed = false") // todo
@ToString(exclude = {"students", "topic"})
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // todo
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

    @Column(name = "is_completed") // todo
    private boolean isDeleted;

    @JsonIgnore
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    @ManyToOne
    private Topic topic;

    @ManyToMany(mappedBy = "tasks")
    @JsonIgnoreProperties("tasks")
    private Set<Student> students = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(imagePath, task.imagePath)
                && Objects.equals(level, task.level) && Objects.equals(text, task.text)
                && Objects.equals(answer, task.answer) && Objects.equals(title, task.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imagePath, level, text, answer, title);
    }
}
