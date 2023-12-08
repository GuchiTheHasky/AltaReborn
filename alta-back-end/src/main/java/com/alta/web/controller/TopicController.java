package com.alta.web.controller;

import com.alta.dto.TopicDto;
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
    private final TopicService TOPIC_SERVICE;

    @GetMapping
    public List<TopicDto> findAll() {
        return TOPIC_SERVICE.findAll();
    }

    @PostMapping("/save")
    public TopicDto save(@RequestBody TopicDto topicDto) {
        return TOPIC_SERVICE.save(topicDto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        TOPIC_SERVICE.delete(id);
    }

    @PutMapping("/update/{id}")
    public TopicDto update(@PathVariable("id") int id, @RequestBody TopicDto topicDto) {
        return TOPIC_SERVICE.update(id, topicDto);
    }
}
