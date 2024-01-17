package com.alta.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@NamedEntityGraph(
        name = "topic-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("tasks")
        }
)
@Entity
@Table(name = "topic")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "tasks")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String title;

    @JdbcTypeCode(SqlTypes.JSON)
    @OneToMany(mappedBy = "topic", fetch = FetchType.LAZY, orphanRemoval = true)
    //@JsonIgnore//Properties("topic")
    List<Task> tasks = new ArrayList<>();
//    @Override
//    public String toString() {
//        return "Topic: {" + '\n' +
//                "id=" + id + '\n' +
//                ", title='" + title + '\n' +
//                ", tasks=" + tasks + '}';
//    }
}


//    @JdbcTypeCode(SqlTypes.JSON)
//    @Column(name = "tasks_ids")
//    private List<Integer> tasksIds = new ArrayList<>();