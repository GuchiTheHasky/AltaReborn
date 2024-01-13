package com.alta.service.impl;

import com.alta.dto.TaskDto;
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
    public TaskDto update(int id, TaskDto taskDto) {
        return taskRepository.findById(id)
                .map(taskRequired -> {
                    taskRequired.setImagePath(taskDto.getImagePath());
                    taskRequired.setLevel(taskDto.getLevel());
                    taskRequired.setText(taskDto.getText());
//                    taskRequired.setTextHtml(taskDto.getTextHtml());
                    taskRequired.setAnswer(taskDto.getAnswer());
                    return taskMapper.toTaskDto(taskRepository.save(taskRequired));
                })
                .orElseThrow(() -> new TaskException(id));
    }

    @Override
    public List<TaskDto> findAllByIds(List<Integer> taskIds) {
        return taskRepository.findAllByIds(taskIds).stream().map(taskMapper::toTaskDto).collect(Collectors.toList());
    }

    @Override
    public TaskDto findById(Integer taskId) {
        return taskMapper.toTaskDto(taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskException(taskId)));
    }
}
