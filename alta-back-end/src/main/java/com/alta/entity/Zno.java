package com.alta.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
public class Zno {
    @Id
    @SequenceGenerator(name = "zno_id_seq", sequenceName = "zno_id_seq",
            initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "zno_id_seq")
    private int id;

    private String name;

    private int year;
}
