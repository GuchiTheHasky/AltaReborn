package com.alta.web.controller;

import com.alta.dto.TaskDto;
import com.alta.service.TaskService;
import com.alta.web.entity.FilteredTasks;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Log4j2
class TaskControllerTest {

    private static final Logger logger = LogManager.getLogger(TaskControllerTest.class);

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Unit test findAllTasksPageByPage()")
    void testFindAllTasksPageByPage() {
        logger.info("Running testFindAllTasksPageByPage");

        List<Integer> studentIds = new ArrayList<>();
        studentIds.add(1);
        List<Integer> topicIds = new ArrayList<>();
        topicIds.add(1);

        Set<TaskDto> assignedTasks = Set.of(
                new TaskDto(1, "image1.jpg", "easy", "answer1", "Task 1"),
                new TaskDto(2, "image2.jpg", "medium", "answer2", "Task 2")
        );
        Set<TaskDto> enabledTasks = Set.of(
                new TaskDto(3, "image3.jpg", "medium", "answer3", "Task 3"),
                new TaskDto(4, "image4.jpg", "hard", "answer4", "Task 4")
        );

        when(taskService.enabledTasks(List.of(1, 2), topicIds)).thenReturn(enabledTasks);

        when(taskService.assignedTasks(anyList(), anyList())).thenReturn(assignedTasks);

        FilteredTasks result = taskController.findAllTasksPageByPage(studentIds, topicIds);

        verify(taskService).assignedTasks(studentIds, topicIds);
        verify(taskService).enabledTasks(List.of(1, 2), topicIds);

        assertEquals(2, result.getAssignedTasks().size());
        assertEquals(2, result.getEnabledTasks().size());
    }


    @Test
    @DisplayName("Unit test byTopicsIds()")
    void testByTopicsIds() {
        logger.info("Running testByTopicsIds");

        List<Integer> topicIds = new ArrayList<>();
        topicIds.add(1);
        List<TaskDto> tasks = List.of(
                new TaskDto(1, "image1.jpg", "easy", "answer1", "Task 1"),
                new TaskDto(2, "image2.jpg", "medium", "answer2", "Task 2")
        );

        when(taskService.findByTopicIds(topicIds)).thenReturn(tasks);

        List<TaskDto> result = taskController.byTopicsIds(topicIds);

        verify(taskService).findByTopicIds(topicIds);

        assertEquals(2, result.size());
    }
}
