package com.alta.mapper;

import com.alta.dto.TaskDto;
import com.alta.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mapping(target = "answer", ignore = true)
    @Mapping(target = "title", ignore = true)
    @Mapping(target = "topic", ignore = true)
    Task toTask(TaskDto taskDto);
    TaskDto toTaskDto(Task task);
}
