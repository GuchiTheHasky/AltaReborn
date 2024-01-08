package com.alta.facade.impl;

import com.alta.dto.TaskDto;
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

    @Override
    public List<TaskDto> findUnfinishedTasks(List<Integer> topicIds, Integer studentId) {
        return taskFilter.filterOfUnfinishedTasks(topicIds, studentId);
    }

    @Override
    public List<TaskDto> updateStudentTasksAndRetrieveDto(int studentId, List<Integer> taskIds) {
        studentService.assigneTasks(studentId, taskIds);
        return taskService.findAllByIds(taskIds);
    }


}
