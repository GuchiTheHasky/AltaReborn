package com.alta.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> subtopics;

    @OneToMany(mappedBy = "topic")
    @JsonIgnore
    private Set<Task> tasks = new HashSet<>();
}
