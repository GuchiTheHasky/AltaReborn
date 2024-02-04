package com.alta.mapper;

import com.alta.dto.TaskDto;
import com.alta.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring"/*, uses = {StudentMapper.class}*/)
public interface TaskMapper {
    @Mapping(target = "answer", ignore = true)
    @Mapping(target = "title", ignore = true)
    Task toTask(TaskDto taskDto);

    TaskDto toTaskDto(Task task);

    List<TaskDto> toTaskDtoList(List<Task> tasks);
}
