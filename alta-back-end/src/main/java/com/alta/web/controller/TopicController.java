package com.alta.web.controller;

import com.alta.dto.TopicDto;
import com.alta.mapper.TopicMapper;
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
    private final TopicMapper topicMapper;

    @GetMapping
    public List<TopicDto> findAll() {
        return topicService.findAll();
    }

    @GetMapping("/{id}")
    public TopicDto findById(@PathVariable("id") int id) {
        return topicMapper.toTopicDto(topicService.findById(id));
    }

    @PostMapping("/save")
    public TopicDto save(@RequestBody TopicDto topicDto) {
        return topicService.save(topicDto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        topicService.delete(id);
    }

    @PutMapping("/update/{id}")
    public TopicDto update(@PathVariable("id") int id, @RequestBody TopicDto topicDto) {
        return topicService.update(id, topicDto);
    }

    @GetMapping("/{name}")
    public TopicDto findByName(@PathVariable("name") String name) {
        return topicMapper.toTopicDto(topicService.findByName(name));
    }
}
