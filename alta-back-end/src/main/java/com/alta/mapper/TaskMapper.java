package com.alta.mapper;

import com.alta.dto.TaskDto;
import com.alta.entity.Task;
import com.alta.entity.Topic;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

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

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "imagePath", ignore = true)
    @Mapping(target = "text", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "students", ignore = true)
    Task update(@MappingTarget Task task, TaskDto taskDto, @Context Topic topic);

    @AfterMapping
    default void afterMapping(@MappingTarget Task task, @Context Topic topic) {
        task.setTopic(topic);
    }

}
