package com.alta.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//@NamedEntityGraph(
//        name = "topic-entity-graph",
//        attributeNodes = {
//                @NamedAttributeNode("tasks")
//        }
//)
@Entity
@Table(name = "topic")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(exclude = "tasks")
//@ToString(exclude = "tasks")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String title;

    //@JsonManagedReference
//    @JdbcTypeCode(SqlTypes.JSON)
//    @OneToMany(mappedBy = "topic", fetch = FetchType.LAZY, orphanRemoval = true)
//    @JsonIgnoreProperties("topic")
//    List<Task> tasks = new ArrayList<>();

//    public void addTask(Task task) {
//        tasks.add(task);
//        task.setTopic(this);
//    }
//    public void removeTask(Task task) {
//        tasks.remove(task);
//        task.setTopic(null);
//    }

}




//    @JdbcTypeCode(SqlTypes.JSON)
//    @Column(name = "tasks_ids")
//    private List<Integer> tasksIds = new ArrayList<>();