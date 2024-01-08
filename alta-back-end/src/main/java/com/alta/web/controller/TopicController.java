package com.alta.web.controller;

import com.alta.dto.TopicDto;
import com.alta.entity.Topic;
import com.alta.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/topics")
@RequiredArgsConstructor
@RestController
public class TopicController {
    private final TopicService topicService; // todo: remove deprecated field, use MainFacade instead;

    @GetMapping
    public List<TopicDto> findAll() {
        return topicService.findAll(); // todo: create method List<TopicDto>findAllTopics() in MainFacade;
    }

    @PostMapping
    public Topic save(@RequestBody Topic topicDto) { // todo: in my opinion, we don't need this method.
        return topicService.save(topicDto);
    }
}
