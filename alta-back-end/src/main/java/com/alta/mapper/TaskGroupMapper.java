package com.alta.mapper;

import com.alta.dto.TasksGroupDto;
import com.alta.entity.TasksGroup;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TaskMapper.class})
public interface TaskGroupMapper {

    TasksGroupDto toDto(TasksGroup tasksGroup);

}
