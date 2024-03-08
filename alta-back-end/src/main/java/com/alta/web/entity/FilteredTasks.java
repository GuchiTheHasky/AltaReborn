package com.alta.web.entity;

import com.alta.dto.TaskDto;

import java.util.Set;

public record FilteredTasks(Set<TaskDto> enabledTasks, Set<TaskDto> assignedTasks) {

}
