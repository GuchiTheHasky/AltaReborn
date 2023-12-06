package com.alta.dto;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

public class TopicDto {

    private int id;
    private String name;
    private List<String> subtopics;
}
