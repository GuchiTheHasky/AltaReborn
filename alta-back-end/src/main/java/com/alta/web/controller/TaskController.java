package com.alta.web.controller;

import com.alta.dto.TaskDto;
import com.alta.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@RestController
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public ModelAndView findAll(String name, ModelMap map) {
        List<TaskDto> tasks = taskService.findAll();
        map.addAttribute("tasks", tasks);
        return new ModelAndView("task_list", map);
    }

    @GetMapping("/answers")
    public ModelAndView findAllWithAnswer(String name, ModelMap map) {
        List<TaskDto> tasksWithAnswers = taskService.findAll();
        map.addAttribute("tasksWithAnswers", tasksWithAnswers);
        return new ModelAndView("task_list_answers", map);
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
