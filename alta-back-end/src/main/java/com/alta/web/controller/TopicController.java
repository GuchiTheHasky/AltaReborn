package com.alta.web.controller;

import com.alta.entity.Topic;
import com.alta.service.TopicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api/v1/topics")
@RequiredArgsConstructor
@RestController
public class TopicController {
    private final TopicService topicService;

    @GetMapping
    public List<Topic> findAll() {
        return topicService.findAll();
    }

    @PostMapping("/save")
    public Topic save(@RequestBody Topic topic){
        return topicService.save(topic);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id){
        topicService.delete(id);
    }

    @PutMapping("/update/{id}")
    public Topic update(@PathVariable("id") int id, @RequestBody Topic topic) {
        return topicService.update(id, topic);
    }

}
