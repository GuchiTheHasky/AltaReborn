package com.alta.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "zno")
@Data
public class Zno {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;
    private int year;
}
