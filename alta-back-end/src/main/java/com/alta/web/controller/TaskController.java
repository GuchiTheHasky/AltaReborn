package com.alta.web.controller;

import com.alta.dto.TaskDto;
import com.alta.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@RestController
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public List<TaskDto> findAll() {
        return taskService.findAll();
    }

    @PostMapping("/save")
    public TaskDto save(@RequestBody TaskDto taskDto) {
        return taskService.save(taskDto);
    }

    @PutMapping("/{taskId}/students/{studentId}")
    public TaskDto addStudentToTask(@PathVariable int taskId, @PathVariable int studentId) {
        return taskService.addStudentToTask(taskId, studentId);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        taskService.delete(id);
    }

    @PutMapping("/update/{id}")
    public TaskDto update(@PathVariable("id") int id, @RequestBody TaskDto taskDto) {
        return taskService.update(id, taskDto);
    }
}
