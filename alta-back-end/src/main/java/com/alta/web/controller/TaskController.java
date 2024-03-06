package com.alta.web.controller;

import com.alta.dto.TaskDto;
import com.alta.handler.ExamHandler;
import com.alta.service.TaskService;
import com.alta.web.entity.FilteredTasks;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@RestController
public class TaskController {
    private final ExamHandler examHandler;
    private final TaskService taskService;


    @GetMapping
    @Operation(
            summary = "Get page of tasks.",
            description = "Retrieves a page of tasks included in topics.", // todo refactor doc
            tags = "Task")
    public FilteredTasks findAllTasksPageByPage(
            @RequestParam(value = "studentIds") List<Integer> studentIds,
            @RequestParam(value = "topicIds") List<Integer> topicIds) {

//        PageRequest pageRequest = PageRequest.of(page, size);
//        Page<TaskDto> tasksPage = mainFacade.findAllTasksPageByPage(studentIds, topicIds, pageRequest);
//        return tasksPage.getContent();
        Set<TaskDto> assignedTasks = taskService.assignedTasks(studentIds, topicIds);
        List<Integer> ids = assignedTasks.stream().map(TaskDto::getId).toList();
        Set<TaskDto> enabledTasks = taskService.enabledTasks(ids, topicIds);

        return new FilteredTasks(enabledTasks, assignedTasks);
    }

    @GetMapping("/by-topics")
    public List<TaskDto> byTopicsIds(@RequestParam("topics") List<Integer> topics) {
        return taskService.findByTopicIds(topics);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update a task.",
            description = "Updates the topic, difficult level and answer of current task.",
            tags = "Task")
    public TaskDto update(@PathVariable int id, @RequestBody TaskDto taskDto) {
        return taskService.update(id, taskDto);
    }
}
