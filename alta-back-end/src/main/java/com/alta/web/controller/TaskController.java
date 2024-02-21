package com.alta.web.controller;

import com.alta.dto.TaskDto;
import com.alta.facade.MainFacade;
import com.alta.web.entity.TaskResponse;
import com.alta.web.entity.TaskRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@RestController
public class TaskController {
    private final MainFacade mainFacade;

    @GetMapping
    @Operation(
            summary = "Get all tasks.",
            description = "Retrieves a list of all tasks available in selected topics.",
            tags = "Task")
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved list of tasks.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = TaskDto.class))
            )
    )
    public List<TaskDto> findAll(
            @RequestParam(value = "studentIds") List<Integer> studentIds,
            @RequestParam(value = "topicIds") List<Integer> topicIds) {
        return mainFacade.findAllTasks(studentIds, topicIds);
    }

    @PostMapping("/assign")
    @Operation(
            summary = "Assign tasks for students.",
            description = "Assign a list of tasks to specified students and returns the task group with current student.",
            tags = "Task")
    @ApiResponse(
            responseCode = "200",
            description = "Tasks successfully assigned to students.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = TaskResponse.class))
            )
    )
    public List<TaskResponse> receiveAssignmentTasks(@RequestBody TaskRequest request) {
        List<Integer> studentIds = request.studentsIds();
        List<Integer> tasksIds = request.tasksIds();

        return mainFacade.receiveAssignmentTasks(studentIds, tasksIds);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update a task.",
            description = "Updates the topic, difficult level and answer of current task.",
            tags = "Task")
    @ApiResponse(
            responseCode = "200",
            description = "Task successfully updated.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = TaskDto.class)
            )
    )
    public TaskDto update(@PathVariable int id, @RequestBody TaskDto taskDto) {
        return mainFacade.updateTask(id, taskDto);
    }
}
