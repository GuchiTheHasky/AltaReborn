package com.alta.mapper;

import com.alta.dto.TaskDto;
import com.alta.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring"/*, uses = {StudentMapper.class}*/)
public interface TaskMapper {
    @Mapping(target = "answer", ignore = true)
    @Mapping(target = "title", ignore = true)
    Task toTask(TaskDto taskDto);

    //@Mapping(target = "students", source = "students")
    TaskDto toTaskDto(Task task);
}
