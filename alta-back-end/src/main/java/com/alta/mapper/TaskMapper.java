package com.alta.mapper;

import com.alta.dto.TaskDto;
import com.alta.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "topic", ignore = true)
    Task toTask(TaskDto taskDto);

    TaskDto toTaskDto(Task task);

    List<TaskDto> toTaskDtoList(List<Task> tasks);

    @Mapping(target = "students", ignore = true)
    @Mapping(target = "topic", ignore = true)
    List<Task> toTaskList(List<TaskDto> tasks);
}
