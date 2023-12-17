package com.alta.service.impl;

import com.alta.dto.TaskDto;
import com.alta.mapper.TaskMapper;
import com.alta.repository.TaskRepository;
import com.alta.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultTaskService implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public List<TaskDto> findAll() {
        return taskRepository.findAll().stream().map(taskMapper::toTaskDto).collect(Collectors.toList());
    }
}
