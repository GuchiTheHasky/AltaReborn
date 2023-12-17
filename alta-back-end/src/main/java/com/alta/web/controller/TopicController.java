package com.alta.web.controller;

import com.alta.dto.TopicDto;
import com.alta.mapper.TopicMapper;
import com.alta.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;


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
}
