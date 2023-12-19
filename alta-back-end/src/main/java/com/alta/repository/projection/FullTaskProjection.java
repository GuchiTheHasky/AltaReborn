package com.alta.repository.projection;

import com.alta.entity.Answer;
import com.alta.entity.Student;
import com.alta.entity.Topic;

import java.util.Set;

public interface FullTaskProjection {
    Integer getId();
    int getNumber();
    String getPathToImage();
    Topic getTopic();
    int getLevel();
    Set<Answer> getCorrectAnswers();
    String getTextFromImage();
    String getHtmlFormat();
    Set<Student> getStudents();
}
