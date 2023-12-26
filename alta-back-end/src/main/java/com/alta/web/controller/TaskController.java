package com.alta.web.controller;

import com.alta.dto.TaskDto;
import com.alta.dto.TopicDto;
import com.alta.entity.Topic;
import com.alta.service.TaskService;
import com.alta.service.TopicService;
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
    private final TopicService topicService;

//    @GetMapping("/all")
//    public List<Topic> getTasksForTopics(@RequestParam(name = "topicIds") List<Integer> topicIds) {
//        return topicService.getTasksFromTopics(topicIds);
//    }
    
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
    public ModelAndView findAllWithAnswer(String name, ModelMap map) {
        List<TaskDto> tasksWithAnswers = taskService.findAll();
        map.addAttribute("tasksWithAnswers", tasksWithAnswers);
        return new ModelAndView("task_list_answers", map);
    }



    @PutMapping("/update/{id}")
    public TaskDto update(@PathVariable("id") int id, @RequestBody TaskDto taskDto) {
        return taskService.update(id, taskDto);
    }
}
