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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultTaskService implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    @Override
    @Transactional
    public TaskDto update(TaskDto taskDto) { // todo: fix it
        Task existingTask = taskRepository.findById(taskDto.getId())
                .orElseThrow(() -> new TaskException("Task not found with id: " + taskDto.getId()));

        Topic newTopic = topicRepository.findById(Integer.valueOf(taskDto.getTitle())) // todo: change exception
                .orElseThrow(() -> new TopicException("Topic not found with title: " + taskDto.getTitle()));

        existingTask.setLevel(taskDto.getLevel());
        existingTask.setAnswer(taskDto.getAnswer());
        existingTask.setTitle(newTopic.getTitle());

        //existingTask.removeTopic(existingTask.getTopic());
        existingTask.setTopic(newTopic);

        return taskMapper.toTaskDto(taskRepository.save(existingTask));
    }

    @Override
    public List<TaskDto> findAllByIds(List<Integer> taskIds) {
        return taskRepository.findAllByIds(taskIds).stream()
                .filter(task -> !task.isDeleted()).map(taskMapper::toTaskDto).collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> getUnfinishedTasks(List<TopicDto> selectedTopics, List<Task> completedTasks) {
        List<Task> tasks = taskRepository.findAllTasksIncludedInTopics(topicMapper.toTopicList(selectedTopics));

        return tasks.stream()
                .distinct()
                .filter(task -> !completedTasks.contains(task))
                .map(taskMapper::toTaskDto).collect(Collectors.toList());
    }

    @Override
    public List<Task> findAllById(List<Integer> tasks) {
        return taskRepository.findAllById(tasks);
    }

    @Override
    public List<TaskDto> getTasksCompletedByAtLeastOneStudent(List<TopicDto> selectedTopics, List<Task> completedTasks) {
        List<Task> tasks = taskRepository.findAllTasksIncludedInTopics(topicMapper.toTopicList(selectedTopics));

        return tasks.stream()
                .distinct()
                .filter(completedTasks::contains)
                .map(taskMapper::toTaskDto).collect(Collectors.toList());
    }

    @Override
    public List<Task> excludeCompletedTasks(List<Task> tasks, Student student) {
        return tasks.stream()
                .filter(task -> student.getTasks().stream()
                        .noneMatch(t -> t.getId() == task.getId()))
                .toList();
    }

}
