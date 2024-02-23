package com.alta.mapper;

import com.alta.dto.TaskDto;
import com.alta.entity.Task;
import com.alta.entity.Topic;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskDto toTaskDto(Task task);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "imagePath", ignore = true)
    @Mapping(target = "text", ignore = true)
    @Mapping(target = "status", ignore = true)
    Task update(@MappingTarget Task task, TaskDto taskDto, @Context Topic topic);

    @AfterMapping
    default void afterMapping(@MappingTarget Task task, @Context Topic topic) {
        task.setTopic(topic);
    }

}
