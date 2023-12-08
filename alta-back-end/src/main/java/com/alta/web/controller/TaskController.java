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
    private final TaskService TASK_SERVICE;

    @GetMapping
    public List<TaskDto> findAll() {
        return TASK_SERVICE.findAll();
    }

    @PostMapping("/save")
    public TaskDto save(@RequestBody TaskDto taskDto) {
        return TASK_SERVICE.save(taskDto);
    }

    @PutMapping("/{taskId}/students/{studentId}")
    public TaskDto addStudentToTask(@PathVariable int taskId, @PathVariable int studentId) {
        return TASK_SERVICE.addStudentToTask(taskId, studentId);
    }

    @GetMapping("/students/{studentId}")
    public List<TaskDto> getTasksByStudentId(@PathVariable int studentId) {
        return TASK_SERVICE.getTasksByStudentId(studentId);
    }

    @PutMapping("/{taskId}/topics/{topicId}")
    public TaskDto assignTopicToTask(@PathVariable int taskId, @PathVariable int topicId) {
        return TASK_SERVICE.assignTopicToTask(taskId, topicId);
    }

    @GetMapping("/topics/{topicId}")
    public List<TaskDto> getTasksByTopicId(@PathVariable int topicId) {
        return TASK_SERVICE.getTasksByTopicId(topicId);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        TASK_SERVICE.delete(id);
    }

    @PutMapping("/update/{id}")
    public TaskDto update(@PathVariable("id") int id, @RequestBody TaskDto taskDto) {
        return TASK_SERVICE.update(id, taskDto);
    }
}
