package com.alta.web.controller;

import com.alta.dto.TaskDto;
import com.alta.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@RestController
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public List<TaskDto> findAll() {
        return taskService.findAll();
    }

}
