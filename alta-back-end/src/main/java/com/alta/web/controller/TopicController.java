package com.alta.web.controller;

import com.alta.dto.TopicDto;
import com.alta.service.TopicService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static com.alta.web.util.PageableValidator.pageableValidation;

@RequestMapping("/api/v1/topics")
@RequiredArgsConstructor
@RestController
public class TopicController {
    private final TopicService topicService;

    @GetMapping
    @Operation(
            summary = "Get all topics",
            description = "Get all topics with optional pagination.",
            tags = "Topic")
    public List<TopicDto> findAll(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        pageableValidation(page, size);

        return topicService.findAll(page, size);
    }
}
