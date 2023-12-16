package com.alta.mapper;

import com.alta.dto.TaskDto;
import com.alta.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mapping(target = "number", ignore = true)
    @Mapping(target = "students", ignore = true)
    Task toTask(TaskDto taskDto);

    TaskDto toTaskDto(Task task);
}
