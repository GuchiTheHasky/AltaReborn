package com.alta.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.util.List;

@Entity
@Table(schema="alta_reborn", name="topic")
@Data
@EqualsAndHashCode(exclude = {"tasks"})
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id")
    private int topicId;

    private String title;

    @JdbcTypeCode(SqlTypes.JSON)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "topic")
    @Column(name = "tasks")
    private List<Task> tasks;
}
