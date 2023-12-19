package com.alta.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashSet;
import java.util.Set;

//@EqualsAndHashCode(exclude = "students")
//@ToString(exclude = "students")
@Data
@Entity
@Table(schema="alta_reborn", name="task")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskId;

    private String description;

    private String answer;

    @Column(name="image_path")
    private String imagePath;

    private String level;

    @ManyToOne
    private Topic topic;

    @ManyToOne
    private Student student;
}


// @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name="topic_id", referencedColumnName = "id")
//    private Topic topic;
//
//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(
//            name="task_student",
//            joinColumns = @JoinColumn(name="task_id"),
//            inverseJoinColumns = @JoinColumn(name="student_id")
//    )
//    private Set<Student> students = new HashSet<>();