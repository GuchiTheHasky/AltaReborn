package com.alta.web.controller;

import com.alta.dto.StudentDto;
import com.alta.dto.TasksGroupDto;
import com.alta.entity.TasksGroup;
import com.alta.facade.MainFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/task-group")
public class TaskGroupController {
    private final MainFacade mainFacade;

    @GetMapping("/{id}")
    @Operation(
            summary = "Find a task group by ID.",
            description = "Retrieves a task group by its unique identifier.",
            tags = "TaskGroup")

    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved the task group.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = TasksGroup.class))
    )
    public TasksGroupDto findById(@PathVariable("id") int id) {
        return mainFacade.findTaskGroupById(id);
    }


    @GetMapping("/studentsIds")
    @Operation(
            summary = "Find task groups by student IDs.",
            description = "Retrieves a list of task groups associated with the specified student IDs.",
            tags = "TaskGroup")
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved the list of task groups.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = TasksGroup.class))
            )
    )
    public List<TasksGroupDto> findByStudentsIds(@RequestParam("id") List<Integer> studentsIds) {
        return mainFacade.findTasksGroupByStudentIds(studentsIds);
    }


    @GetMapping("/{id}/document")
    @Operation(
            summary = "Get template for a task group.",
            description = "Retrieves a template for a task group by its ID and the specified document name(type): \"with_answer\" or \"without_answer\".",
            tags = "TaskGroup")
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved the template with student & task group.",
            content = @Content(mediaType = "application/html")
    )
    public ModelAndView getModelAndView(
            @PathVariable("id") int taskGroupId,
            @RequestParam("type") String type) {
        TasksGroupDto group = mainFacade.findTaskGroupById(taskGroupId);
        StudentDto student = mainFacade.findStudentById(group.getStudentId());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(type);
        modelAndView.addObject("student", student);
        modelAndView.addObject("group", group);
        return modelAndView;
    }
}
