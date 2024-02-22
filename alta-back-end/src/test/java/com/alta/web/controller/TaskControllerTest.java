package com.alta.web.controller;

import com.alta.dto.StudentDto;
import com.alta.dto.TaskDto;
import com.alta.dto.TasksGroupDto;
import com.alta.facade.MainFacade;
import com.alta.web.entity.TaskRequest;
import com.alta.web.entity.TaskResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class TaskControllerTest {

    @Mock
    private MainFacade mainFacade;

    @InjectMocks
    private TaskController taskController;

    @Test
    @DisplayName("Unit test findAll() for TaskDto, check size and values")
    void findAll_ReturnsListOfTasks() {
        List<Integer> studentIds = Arrays.asList(1, 2, 3);
        List<Integer> topicIds = Arrays.asList(4, 5, 6);

        List<TaskDto> tasks = Arrays.asList(
                new TaskDto(1, "image1.jpg", "Level_1", "text_1", "Answer 1", "Title 1"),
                new TaskDto(2, "image2.jpg", "Level_2", "text_1", "Answer 2", "Title 2")
        );

        when(mainFacade.findAllTasks(studentIds, topicIds)).thenReturn(tasks);

        List<TaskDto> result = taskController.findAll(studentIds, topicIds);

        verify(mainFacade).findAllTasks(studentIds, topicIds);

        assertEquals(2, result.size());
        assertEquals(tasks.get(0), result.get(0));
        assertEquals(tasks.get(1), result.get(1));
    }

    @Test
    @DisplayName("Unit test findAllTasksPageByPage() for TaskDto, check size and values")
    void findAllTasksPageByPage_ReturnsPageOfTasks() {
        List<Integer> studentIds = Arrays.asList(1, 2, 3);
        List<Integer> topicIds = Arrays.asList(4, 5, 6);
        PageRequest pageRequest = PageRequest.of(0, 10);

        List<TaskDto> tasks = Arrays.asList(
                new TaskDto(1, "image1.jpg", "Level_1", "text_1", "Answer 1", "Title 1"),
                new TaskDto(2, "image2.jpg", "Level_2", "text_1", "Answer 2", "Title 2")
        );
        Page<TaskDto> taskPage = new PageImpl<>(tasks);

        when(mainFacade.findAllTasksPageByPage(studentIds, topicIds, pageRequest)).thenReturn(taskPage);

        List<TaskDto> result = taskController.findAllTasksPageByPage(studentIds, topicIds, 0, 10);

        verify(mainFacade).findAllTasksPageByPage(studentIds, topicIds, pageRequest);

        assertEquals(2, result.size());
        assertEquals(tasks.get(0), result.get(0));
        assertEquals(tasks.get(1), result.get(1));
    }

    @Test
    @DisplayName("Unit test receiveAssignmentTasks() for TaskResponse, check size and values")
    void receiveAssignmentTasks_ReturnsListOfTaskResponses() {
        List<Integer> studentIds = Arrays.asList(1, 2);
        List<Integer> tasksIds = Arrays.asList(1, 2);
        TaskRequest request = new TaskRequest(studentIds, tasksIds);

        List<TasksGroupDto> tasksGroups = Arrays.asList(
                new TasksGroupDto(1, LocalDateTime.now(), 1, Arrays.asList(
                        new TaskDto(1, "image1.jpg", "Level_1", "text_1", "Answer 1", "Title 1"),
                        new TaskDto(2, "image2.jpg", "Level_2", "text_2", "Answer 2", "Title 2")
                )),
                new TasksGroupDto(2, LocalDateTime.now(), 2, Arrays.asList(
                        new TaskDto(3, "image3.jpg", "Level_3", "text_3", "Answer 3", "Title 3"),
                        new TaskDto(4, "image4.jpg", "Level_4", "text_4", "Answer 4", "Title 4")
                ))
        );

        List<TaskResponse> taskResponses = tasksGroups.stream()
                .map(group -> new TaskResponse(
                        new StudentDto(group.getStudentId(), "Гурченко Катерина", "katerina.gurchenko@example.com", "Grade A", "No comments"),
                        group))
                .collect(Collectors.toList());

        when(mainFacade.receiveAssignmentTasks(request.studentsIds(), request.tasksIds())).thenReturn(taskResponses);

        List<TaskResponse> result = taskController.receiveAssignmentTasks(request);

        verify(mainFacade).receiveAssignmentTasks(request.studentsIds(), request.tasksIds());

        assertEquals(2, result.size());
        assertEquals(taskResponses.get(0), result.get(0));
        assertEquals(taskResponses.get(1), result.get(1));
    }

    @Test
    @DisplayName("Unit test update() for TaskDto, check returned task")
    void update_ReturnsUpdatedTask() {

        int taskId = 1;
        TaskDto updatedTask = new TaskDto(taskId, "updated_image.jpg", "Updated_Level", "updated_text", "Updated_Answer", "Updated_Title");

        when(mainFacade.updateTask(taskId, updatedTask)).thenReturn(updatedTask);

        TaskDto result = taskController.update(taskId, updatedTask);

        verify(mainFacade).updateTask(taskId, updatedTask);
        assertEquals(updatedTask, result);
    }

}