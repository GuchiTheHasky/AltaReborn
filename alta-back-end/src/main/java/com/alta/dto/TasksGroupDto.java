package com.alta.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TasksGroupDto {
    private int id;
    private LocalDateTime creatingAt;
    private int studentId;
    private List<TaskDto> tasks = new ArrayList<>();
}
