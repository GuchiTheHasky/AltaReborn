package com.alta.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.*;

@Entity
@Table(name = "students")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;
    private String grade;
    private String comment;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonBackReference                           // todo: I suppose we don't need this anymore. Make sure and delete if so.
    private List<Task> tasks = new ArrayList<>();// todo: Also drop redundant column in the table (use migration).

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "tasks_ids")
    private List<Integer> tasksIds = new ArrayList<>();
}
