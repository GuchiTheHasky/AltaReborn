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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    @SequenceGenerator(name="id_generator", sequenceName = "topic_id_sequence", initialValue = 1, allocationSize = 20)
    private int id;

    private String title;

    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> subtopics;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "topic")
    @JsonIgnore
    private Set<Task> tasks = new HashSet<>();
}
