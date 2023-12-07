package com.alta.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@EqualsAndHashCode(exclude = "tasks")
@ToString(exclude = "tasks")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;
    private String grade;
    private String status;

    @ManyToMany(mappedBy = "students")
    @JsonIgnore
    private Set<Task> tasks = new HashSet<>();
}
