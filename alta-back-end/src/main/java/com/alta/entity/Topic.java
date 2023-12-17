package com.alta.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name="topic")
@Table(schema="alta")
@Data
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    @SequenceGenerator(name="id_generator", sequenceName = "topic_id_sequence", initialValue = 1, allocationSize = 20)
    private int id;

    private String name;

    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> subtopics;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "topic")
    private Set<Task> tasks = new HashSet<>();
}
