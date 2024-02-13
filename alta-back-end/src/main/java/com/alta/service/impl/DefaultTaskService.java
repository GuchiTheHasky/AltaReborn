package com.alta.service.impl;

import com.alta.dto.TaskDto;
import com.alta.dto.TopicDto;
import com.alta.entity.Student;
import com.alta.entity.Task;
import com.alta.entity.Topic;
import com.alta.exception.TaskException;
import com.alta.exception.TopicException;
import com.alta.mapper.TaskMapper;
import com.alta.mapper.TopicMapper;
import com.alta.repository.TaskRepository;
import com.alta.repository.TopicRepository;
import com.alta.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DefaultTaskService implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    @Override
    public List<TaskDto> getUnfinishedTasks(List<TopicDto> selectedTopics, Set<Task> completedTasks) {
        List<Task> tasks = taskRepository.findAllTasksIncludedInTopics(topicMapper.toTopicList(selectedTopics));

        return tasks.stream()
                .distinct()
                .filter(task -> !completedTasks.contains(task))
                .map(taskMapper::toTaskDto).toList();
    }

    @Override
    public List<TaskDto> getTasksCompletedByAtLeastOneStudent(List<TopicDto> selectedTopics, Set<Task> completedTasks) {
        List<Task> tasks = taskRepository.findAllTasksIncludedInTopics(topicMapper.toTopicList(selectedTopics));

        return tasks.stream()
                .distinct()
                .filter(completedTasks::contains)
                .map(taskMapper::toTaskDto).toList();
    }

    @Override
    public List<Task> excludeCompletedTasks(List<Task> tasks, Student student) {
        return tasks.stream()
                .filter(task -> !student.getTasks().contains(task))
                .toList();
    }

    @Override
    @Transactional
    public TaskDto update(int id, TaskDto taskDto) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new TaskException(id));

        Topic newTopic = topicRepository.findByTitle(taskDto.getTitle())
                .orElseThrow(() -> new TopicException(taskDto.getTitle()));

        existingTask.setLevel(taskDto.getLevel());
        existingTask.setAnswer(taskDto.getAnswer());
        existingTask.setTitle(newTopic.getTitle());
        existingTask.setTopic(newTopic);

        return taskMapper.toTaskDto(taskRepository.save(existingTask));
    }
}
