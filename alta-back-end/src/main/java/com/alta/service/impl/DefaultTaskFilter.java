package com.alta.service.impl;

import com.alta.dto.TaskDto;
import com.alta.entity.Student;
import com.alta.service.StudentService;
import com.alta.service.TaskFilter;
import com.alta.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultTaskFilter implements TaskFilter {
    private final StudentService studentService;
    private final TaskService taskService;


    @Override
    public List<TaskDto> filterOfUnfinishedTasks(List<Integer> topicIds, Integer studentId) {
        Student student = studentService.findById(studentId);
        List<Integer> tasksIds = student.getTasksIds();

        return taskService.filterOfUnfinishedTasks(topicIds, tasksIds);
    }


}
