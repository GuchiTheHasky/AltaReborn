package com.alta.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ZnoDto {
    private int id;

    private String name;

    private String year;

    private List<TaskDto> tasks = new ArrayList<>();
}
