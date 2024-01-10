package com.alta.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "topic")
@Data
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String title;

    @JsonManagedReference
    @JdbcTypeCode(SqlTypes.JSON)
    @OneToMany(mappedBy = "topic")
    private List<Task> tasks = new ArrayList<>();

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "tasks_ids")
    private List<Integer> tasksIds = new ArrayList<>();
}
