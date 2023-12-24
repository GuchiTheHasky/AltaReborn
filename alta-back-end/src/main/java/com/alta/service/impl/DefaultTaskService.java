package com.alta.service.impl;

import com.alta.dto.TaskDto;
import com.alta.entity.Task;
import com.alta.exception.TaskException;
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

    @Override
    public TaskDto save(TaskDto taskDto) {
        Task newTask = taskMapper.toTask(taskDto);
        return taskMapper.toTaskDto(taskRepository.save(newTask));
    }

    @Override
    public TaskDto update(int id, TaskDto taskDto) {
        return taskRepository.findById(id)
                .map(taskRequired -> {
                    taskRequired.setImagePath(taskDto.getImagePath());
                    taskRequired.setLevel(taskDto.getLevel());
                    taskRequired.setText(taskDto.getText());
                    taskRequired.setTextHtml(taskDto.getTextHtml());
                    taskRequired.setAnswer(taskDto.getAnswer());
                    return taskMapper.toTaskDto(taskRepository.save(taskRequired));
                })
                .orElseThrow(() -> new TaskException(id));
    }

    Task findById(int taskId) {
        return taskRepository.findById(taskId).orElseThrow(() -> new TaskException(taskId));
    }

}
