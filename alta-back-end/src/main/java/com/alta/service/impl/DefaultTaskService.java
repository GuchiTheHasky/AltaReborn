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
import java.util.Set;
import java.util.stream.Collectors;

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

    @Override
    public Set<TaskDto> assignedTasks(List<Integer> students, List<Integer> topics) {
        return taskRepository.findTasksAtLeastOneStudentAttempted(students, topics)
                .stream().map(taskMapper::toTaskDto)
                .collect(Collectors.toSet());
    }
    @Override
    public Set<TaskDto> enabledTasks(List<Integer> ids, List<Integer> topics) {
        return taskRepository.findNotAttemptedTasks(ids, topics)
                .stream().map(taskMapper::toTaskDto)
                .collect(Collectors.toSet());
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
