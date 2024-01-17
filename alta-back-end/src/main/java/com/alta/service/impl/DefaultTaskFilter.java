package com.alta.service.impl;

import com.alta.dto.TaskDto;
import com.alta.entity.Student;
import com.alta.entity.Task;
import com.alta.mapper.TaskMapper;
import com.alta.repository.TaskRepository;
import com.alta.service.StudentService;
import com.alta.service.TaskFilter;
import com.alta.service.TaskService;
import com.alta.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultTaskFilter implements TaskFilter {
    private final StudentService studentService;
    private final TaskService taskService;
    private final TopicService topicService;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;


    @Override
    public List<TaskDto> filterOfUnfinishedTasks(List<Integer> selectedTopicsIdList, Integer studentId) {
        Student student = studentService.findById(studentId);
        List<Task> completedTasks = student.getTasks();

        List<Task> tasks = taskRepository.findAllTaskIncludedInTopic(selectedTopicsIdList);

        Set<Task> uniqueTasksSet = new HashSet<>(tasks);
        uniqueTasksSet.removeAll(completedTasks);
        List<Task> uniqueTasks = new ArrayList<>(uniqueTasksSet);

        return uniqueTasks.stream().map(taskMapper::toTaskDto).collect(Collectors.toList());

//        return topicService.findAllByIds(topicIds).stream()
//                .flatMap(topic -> topic.getTasksIds().stream())
//                .filter(taskId -> !tasksIds.contains(taskId))
//                .map(taskService::findTaskById)
//                .filter(task -> !task.isDeleted())
//                .map(taskMapper::toTaskDto)
//                .collect(Collectors.toList());
    }

    @Override
    public List<Integer> filterOfUniqueTasks(List<Integer> tasksIds, Integer studentId) {
        Student student = studentService.findById(studentId);
        List<Integer> studentTasksIds = student.getTasksIds();

        return tasksIds.stream()
                .filter(taskId -> !studentTasksIds.contains(taskId))
                .collect(Collectors.toList());
    }
}
