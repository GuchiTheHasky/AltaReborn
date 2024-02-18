package com.alta.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"tasks_groups"})
@EqualsAndHashCode(exclude = "tasks_groups")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "grade")
    private String grade;

    @Column(name = "comment")
    private String comment;

//    @ManyToMany
//    @JoinTable(
//            name = "student_task",
//            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"))
//    private Set<Task> tasks = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "students_tasks_group",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tasks_group_id", referencedColumnName = "id"))
    private Set<Task> tasks_groups = new HashSet<>();
}
