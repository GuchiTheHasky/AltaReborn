package com.alta.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "task")
@ToString(exclude = {"topic", "student", "isCompleted"})
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "image_path")
    private String imagePath;

    private String level;
    private String text;

    @Column(name = "texthtml")
    private String textHtml;
    private String answer;
    private String title;

    @Column(name = "is_completed")
    private boolean isCompleted; // todo: rename to isCompleted. Also drop redundant column in the table (use migration).

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    private Topic topic;

    //@JdbcTypeCode(SqlTypes.JSON)
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(name = "student_id", referencedColumnName = "id" )
    private Student student;    // todo: I suppose we don't need this anymore. Make sure and delete if so.
                                // todo: Also drop redundant column in the table (use migration).


}

