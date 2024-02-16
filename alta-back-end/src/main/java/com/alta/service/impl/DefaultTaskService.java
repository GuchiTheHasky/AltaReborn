package com.alta.service.impl;

import com.alta.dto.TaskDto;
import com.alta.dto.TopicDto;
import com.alta.entity.Student;
import com.alta.entity.Task;
import com.alta.entity.TaskStatus;
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

@Service
@RequiredArgsConstructor
public class DefaultTaskService implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

//    @Override
//    public List<TaskDto> getUnfinishedTasks(List<TopicDto> selectedTopics, List<Task> completedTasks) {
//        List<Task> tasks = taskRepository.findAllTasksIncludedInTopics(topicMapper.toTopicList(selectedTopics));
//
//        return tasks.stream()
//                .distinct()
//                .filter(task -> !completedTasks.contains(task))
//                .map(taskMapper::toTaskDto).toList();
//    }
//
//    @Override
//    public List<TaskDto> getTasksCompletedByAtLeastOneStudent(List<TopicDto> selectedTopics, List<Task> completedTasks) {
//        List<Task> tasks = taskRepository.findAllTasksIncludedInTopics(topicMapper.toTopicList(selectedTopics));
//
//        return tasks.stream()
//                .distinct()
//                .filter(completedTasks::contains)
//                .map(taskMapper::toTaskDto).toList();
//    }

    @Override
    public List<Task> excludeCompletedTasks(List<Task> tasks, Student student) {
        return tasks.stream()
                .filter(task -> !student.getTasks().contains(task))
                .toList();
    }

    @Override
    @Transactional
    public TaskDto update(int id, TaskDto taskDto) {
        Task existingTask = findTask(id);

        Topic newTopic = findTopic(taskDto);

        return taskMapper.toTaskDto(taskRepository.save(taskMapper.update(existingTask, taskDto, newTopic)));
    }

    @Override
    @Transactional
    public List<TaskDto> findAllTasks(List<Integer> topicsIds, List<Integer> studentsIds) {

        List<Task> tasks = taskRepository.findAllTasks(topicsIds);

        List<TaskDto> tasksDto = tasks.stream().map(taskMapper::toTaskDto).toList();

        tasksDto.forEach(taskDto -> {
            if (!taskDto.getStudents().isEmpty()) {
                taskDto.setStatus(TaskStatus.valueOf("ASSIGNED"));
            }
        });
        return tasksDto;
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
