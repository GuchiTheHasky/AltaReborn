package com.alta.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Zno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int year;
}
