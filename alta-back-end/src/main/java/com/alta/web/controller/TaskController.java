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

    @PostMapping("/save")
    public TaskDto save(@RequestBody TaskDto taskDto) {
        return taskService.save(taskDto);
    }

    @PutMapping("/{taskId}/students/{studentId}")
    public TaskDto assignStudentToTask(@PathVariable int taskId, @PathVariable int studentId) {
        return taskService.assignStudentToTask(taskId, studentId);
    }

    @GetMapping("/students/{studentId}")
    public List<TaskDto> findAllByStudentId(@PathVariable int studentId) {
        return taskService.findAllByStudentId(studentId);
    }

    @PutMapping("/{taskId}/topics/{topicId}")
    public TaskDto assignTopicToTask(@PathVariable int taskId, @PathVariable int topicId) {
        return taskService.assignTopicToTask(taskId, topicId);
    }

    @GetMapping("/topics/{topicId}")
    public List<TaskDto> findAllByTopicId(@PathVariable int topicId) {
        return taskService.findAllByTopicId(topicId);
    }

    @GetMapping("/topics/")
    public List<TaskDto> findAllByTopicIdList(@RequestBody List<Integer> topicIdList) {
        return taskService.findAllByTopicIdList(topicIdList);
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
