package com.alta.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Zno")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "tasks")
public class Zno {
    @Id
    private int id;
    private String name;
    private String year;
    @OneToMany(mappedBy = "zno")
    private List<Task> tasks;
}
