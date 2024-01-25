package com.alta.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.*;

@Entity
@Table(name = "students")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"tasks"})
@EqualsAndHashCode(exclude = "tasks")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stud_seq")
    @SequenceGenerator(name = "stud_seq", sequenceName = "student_id_sequence", allocationSize = 10)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;
    private String grade;
    private String comment;

//    @JdbcTypeCode(SqlTypes.JSON)
//    @Column(name = "tasks_ids")
//    private List<Integer> tasksIds = new ArrayList<>();

 //   @ManyToMany(mappedBy = "students")
    @ManyToMany
    @JoinTable(
            name = "student_task",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id" , foreignKey = @ForeignKey(name = "none")))
    private List<Task> tasks = new ArrayList<>();


    public void addTask(Task task) {
        tasks.add(task);
        task.getStudents().add(this);
    }


}
