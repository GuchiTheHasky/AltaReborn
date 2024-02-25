package com.alta.service.impl;

import com.alta.dto.TaskDto;
import com.alta.entity.Task;
import com.alta.entity.Topic;
import com.alta.exception.TaskException;
import com.alta.exception.TopicException;
import com.alta.mapper.TaskMapper;
import com.alta.repository.TaskRepository;
import com.alta.repository.TopicRepository;
import com.alta.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultTaskService implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final TopicRepository topicRepository;

    @Override
    public List<TaskDto> findByTopicIds(List<Integer> topicsIds) {
        return taskRepository.findByTopicIds(topicsIds).stream().map(taskMapper::toTaskDto).toList();
    }

    @Override
    public List<Task> findAllByIds(List<Integer> tasksIds) {
        return taskRepository.findAllById(tasksIds);
    }

    @Override
    @Transactional
    public TaskDto update(int id, TaskDto taskDto) {
        Task existingTask = findTask(id);
        Topic newTopic = findTopic(taskDto);

        return taskMapper.toTaskDto(taskRepository.save(taskMapper.update(existingTask, taskDto, newTopic)));
    }

    private Topic findTopic(TaskDto taskDto) {
        return topicRepository.findByTitle(taskDto.getTitle())
                .orElseThrow(() -> new TopicException(taskDto.getTitle()));
    }

    private Task findTask(int id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskException(id));
    }
}
