package com.alta.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(schema="alta_reborn", name="student")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"tasks"})
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private int studentId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;
    private String grade;
    private String comment;

    @JdbcTypeCode(SqlTypes.JSON)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private Set<Task> tasks;

}
