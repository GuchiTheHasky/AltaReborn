package com.alta.web.controller;

import com.alta.dto.TaskDto;
import com.alta.dto.TopicDto;
import com.alta.service.TaskService;
import com.alta.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@RestController
public class TaskController {
    private final TaskService taskService;
    private final TopicService topicService;
@GetMapping("/fetch")
public List<TaskDto> fetchData(
        @RequestParam(name = "topics", required = false) List<Integer> topics,
        @RequestParam(name = "student", required = false) Integer studentId) {
    System.out.println("Received request with topics: " + topics + " and studentId: " + studentId);

    return taskService.getTasksFromTopics(topics, studentId);
}
    
    @GetMapping("/all")
    public List<TaskDto> getTasksForTopics() {
        return taskService.findAll();
    }

    @GetMapping("/dto")
    public List<TopicDto> getTasksDtoForTopics() {
        return topicService.findAll();
    }

    @GetMapping
    public ModelAndView findAll(String name, ModelMap map) {
        List<TaskDto> tasks = taskService.findAll();
        map.addAttribute("tasks", tasks);
        return new ModelAndView("task_list", map);
    }

    @GetMapping("/answers")
    public ModelAndView findAllWithAnswer(ModelMap model, @RequestParam(name = "tasks", required = false) List<Integer> tasks) {
        List<TaskDto> tasksWithAnswers = taskService.findAllByIds(tasks);
        model.addAttribute("tasksWithAnswers", tasksWithAnswers);
        return new ModelAndView("task_list_answers", model);
    }



    @PutMapping("/update/{id}")
    public TaskDto update(@PathVariable("id") int id, @RequestBody TaskDto taskDto) {
        return taskService.update(id, taskDto);
    }
}
