package com.alta.service.impl;

import com.alta.dto.TaskDto;
import com.alta.exception.TaskException;
import com.alta.mapper.TaskMapper;
import com.alta.mapper.TopicMapper;
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
    private final TopicMapper topicMapper;

    @Override
    public List<TaskDto> findAll() {
        return taskRepository.findAll().stream().map(taskMapper::toTaskDto).collect(Collectors.toList());
    }

    @Override
    public TaskDto update(int id, TaskDto taskDto) {
        return taskRepository.findById(id)
                .map(taskExisting -> {
                    taskExisting.setDescription(taskDto.getDescription());
                    taskExisting.setTopic(topicMapper.toTopic(taskDto.getTopicDto()));
                    taskExisting.setAnswer(taskDto.getAnswer());
                    taskExisting.setPathToImage(taskDto.getPathToImage());
                    taskExisting.setLevel(taskDto.getLevel());
                    return taskMapper.toTaskDto(taskRepository.save(taskExisting));
                })
                .orElseThrow(() -> new TaskException(id));
    }

    @Override
    public TaskDto findById(int id) {
        return taskMapper.toTaskDto(taskRepository.findById(id).orElseThrow(() -> new TaskException(id)));
    }
}
