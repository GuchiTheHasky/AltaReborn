package com.alta.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.util.List;

@Entity
@Data
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> subtopics;
}
