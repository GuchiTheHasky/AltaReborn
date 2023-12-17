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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    @SequenceGenerator(name="id_generator", sequenceName = "task_id_sequence", initialValue = 1, allocationSize = 20)
    private int id;

    private int number;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="topic_id", referencedColumnName = "id")
    private Topic topic;

    private String answer;

    @Column(name="path_to_image")
    private String pathToImage;

    private String level;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name="task_student",
            joinColumns = @JoinColumn(name="task_id"),
            inverseJoinColumns = @JoinColumn(name="student_id")
    )
    private Set<Student> students = new HashSet<>();
}
