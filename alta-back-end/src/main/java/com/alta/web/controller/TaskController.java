package com.alta.web.controller;

import com.alta.dto.TaskDto;
import com.alta.entity.Topic;
import com.alta.service.TaskService;
import com.alta.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@RestController
public class TaskController {
    private final TaskService taskService;
    private final TopicService topicService;

    @GetMapping("/all")
    public List<Topic> getTasksForTopics(@RequestParam(name = "topicIds") List<Integer> topicIds) {
        return topicService.getTasksFromTopics(topicIds);
    }


    @GetMapping
    public List<TaskDto> findAll() {
        return taskService.findAll();
    }



    @PutMapping("/update/{id}")
    public TaskDto update(@PathVariable("id") int id, @RequestBody TaskDto taskDto) {
        return taskService.update(id, taskDto);
    }
}
