package com.alta.service.impl;

import com.alta.dto.TaskDto;
import com.alta.entity.Student;
import com.alta.service.StudentService;
import com.alta.service.TaskFilter;
import com.alta.service.TaskService;
import com.alta.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultTaskFilter implements TaskFilter {
    private final StudentService studentService;
    private final TaskService taskService;
    private final TopicService topicService;


    @Override
    public List<TaskDto> filterOfUnfinishedTasks(List<Integer> topicIds, Integer studentId) {
        Student student = studentService.findById(studentId);
        List<Integer> tasksIds = student.getTasksIds();

        return topicService.findAllByIds(topicIds).stream()
                .flatMap(topic -> topic.getTasksIds().stream())
                .filter(taskId -> !tasksIds.contains(taskId))
                .map(taskService::findById)
                .collect(Collectors.toList());
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
