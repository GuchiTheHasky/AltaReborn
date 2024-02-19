package com.alta.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TasksGroupDto {
    private int id;
    private LocalDate creationDate;
    private List<TaskDto> tasks = new ArrayList<>();
}
