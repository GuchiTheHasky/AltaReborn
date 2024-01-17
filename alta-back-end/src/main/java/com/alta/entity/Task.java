package com.alta.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "task")
@SQLRestriction("is_completed = false")
@ToString(exclude = {"students"})
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "image_path")
    private String imagePath;

    private String level;
    private String text;

    private String answer;
    private String title;
    //    @Column(name = "topic_id_origin")
    //    private int topicId;
    @Column(name = "is_completed")
    private boolean isDeleted;

    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    @ManyToOne
    private Topic topic;

    @ManyToMany(mappedBy = "tasks")
    //@JsonIgnoreProperties("tasks")
    private List<Student> students = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Task))
            return false;
        return id == ((Task) o).getId() && title.equals(((Task) o).getTitle())
                && answer.equals(((Task) o).getAnswer()) && level.equals(((Task) o).getLevel());
    }

//    @Override
//    public String toString() {
//        return "Task: {" + '\n' +
//                "id=" + id + '\n' +
//                ", imagePath='" + imagePath + '\n' +
//                ", level='" + level + '\n' +
//                ", answer='" + answer + '\n' +
//                ", title='" + title + '\n' + '}';
//    }
}


//    @Column(name = "texthtml")
//    private String textHtml;