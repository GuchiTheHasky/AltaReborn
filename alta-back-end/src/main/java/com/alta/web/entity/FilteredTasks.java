package com.alta.web.entity;

import com.alta.dto.TaskDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FilteredTasks {
    private Set<TaskDto> enabledTasks;
    private Set<TaskDto> assignedTasks;
}
