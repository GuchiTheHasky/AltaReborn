package com.alta.mapper;

import com.alta.dto.TaskDto;
import com.alta.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mapping(target = "number", ignore = true)
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "topic", source = "topicDto")
    Task toTask(TaskDto taskDto);

    @Mapping(target = "topicDto", source = "topic")
    TaskDto toTaskDto(Task task);
}
