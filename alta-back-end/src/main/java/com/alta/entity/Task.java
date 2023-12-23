package com.alta.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = "students")
@ToString(exclude = "students")
@Entity(name="task")
@Table(schema="alta")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int number;

    @Column(name="path_to_image")
    private String pathToImage;

    private String level;

    private String description;
    private String answer;

    @ManyToOne
    @JoinColumn(name="topic_id", referencedColumnName = "id")
    private Topic topic;

    @ManyToMany
    @JoinTable(
            name="task_student",
            joinColumns = @JoinColumn(name="task_id"),
            inverseJoinColumns = @JoinColumn(name="student_id")
    )
    private Set<Student> students = new HashSet<>();

    public void addStudent(Student student) {
        students.add(student);
    }
}
