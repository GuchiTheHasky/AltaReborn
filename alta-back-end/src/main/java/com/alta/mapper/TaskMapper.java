package com.alta.mapper;

import com.alta.dto.TaskDto;
import com.alta.entity.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task toTask(TaskDto taskDto);
    TaskDto toTaskDto(Task task);
}
