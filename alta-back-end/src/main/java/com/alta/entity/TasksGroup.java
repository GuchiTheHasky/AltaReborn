package com.alta.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tasks_group")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"tasks", "students"})
@EqualsAndHashCode(exclude = {"tasks", "students"})
public class TasksGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToMany(mappedBy = "tasks_groups")
    @JsonIgnoreProperties("tasks_groups")
    private Set<Student> students = new HashSet<>();

    @Column(name = "creation_date")
    @CreationTimestamp
    private Date creationDate;

    @ManyToMany
    @JoinTable(
            name = "tasks_group_task",
            joinColumns = @JoinColumn(name = "tasks_group_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"))
    private Set<Task> tasks = new HashSet<>();
}
