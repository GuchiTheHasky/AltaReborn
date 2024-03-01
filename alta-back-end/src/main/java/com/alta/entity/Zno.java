package com.alta.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Entity
@Table(name = "zno")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"tasks"})
public class Zno {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "zno_seq")
    @SequenceGenerator(name = "zno_seq", sequenceName = "zno_id_seq", allocationSize = 20)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private String year;

    @Column(name = "tasks")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<Task> tasks;
}
