package com.alta.web.controller;

import com.alta.dto.TopicDto;
import com.alta.facade.MainFacade;
import com.alta.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/topics")
@RequiredArgsConstructor
@RestController
public class TopicController {
    private final MainFacade mainFacade;
    private final TopicService topicService;

    @GetMapping
    public List<TopicDto> findAll() {
//        return mainFacade.findAllTopics();
        return topicService.findAll();
    }
}
