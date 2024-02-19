package com.alta.mapper;

import com.alta.dto.TasksGroupDto;
import com.alta.entity.TasksGroup;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TasksGroupMapper {

    TasksGroup toTasksGroup(TasksGroupDto tasksGroupDto);

    TasksGroupDto toTasksGroupDto(TasksGroup tasksGroup);
}
