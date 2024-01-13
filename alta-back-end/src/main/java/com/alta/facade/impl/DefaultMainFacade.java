package com.alta.facade.impl;

import com.alta.dto.StudentDto;
import com.alta.dto.TaskDto;
import com.alta.dto.TopicDto;
import com.alta.facade.MainFacade;
import com.alta.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultMainFacade implements MainFacade {

    private final TaskFilter taskFilter;
    private final TaskService taskService;
    private final StudentService studentService;
    private final TopicService topicService;

    @Override
    public List<TaskDto> findUnfinishedTasks(List<Integer> topicIds, Integer studentId) {
        return taskFilter.filterOfUnfinishedTasks(topicIds, studentId);
    }

    @Override
    public List<TaskDto> updateStudentTasksAndRetrieveDto(int studentId, List<Integer> taskIds) {
        List<Integer> tasksToAssign = taskFilter.filterOfUniqueTasks(taskIds, studentId);
        studentService.assignTasks(studentId, tasksToAssign);
        return taskService.findAllByIds(taskIds);
    }

    @Override
    public List<StudentDto> findAllStudents() {
        return studentService.findAll();
    }

    @Override
    public TaskDto updateTask(int id, TaskDto taskDto) {
        return taskService.update(id, taskDto);
    }

    @Override
    public List<TopicDto> findAllTopics() {
        return topicService.findAll();
    }
}
