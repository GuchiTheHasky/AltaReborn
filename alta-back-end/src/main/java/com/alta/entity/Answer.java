package com.alta.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema="alta_reborn", name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String text;
    @Column(name = "answer_order")
    private int order;
}
