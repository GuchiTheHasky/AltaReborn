package com.alta.web.entity;

import com.alta.dto.TaskDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TasksResponse {
    List<TaskDto> unfinishedTasksForAllStudentsSelected;
    List<TaskDto> tasksCompletedByAtLeastOneStudent;
}
