package com.alta.web.controller;

import com.alta.dto.TopicDto;
import com.alta.facade.MainFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/topics")
@RequiredArgsConstructor
@RestController
public class TopicController {
    private final MainFacade mainFacade;

    @GetMapping
    @Operation(
            summary = "Get All Topics",
            description = "Retrieves a list of all topics available in the system.",
            tags = "Topic")
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved list of topics.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = TopicDto.class))))
    public List<TopicDto> findAll() {
        return mainFacade.findAllTopics();
    }

    @GetMapping("/paging")
    public List<TopicDto> findAllTopicsPageByPage(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "15") int size) {

        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Page number and size must be non-negative");
        }

        PageRequest pageRequest = PageRequest.of(page, size);
        return mainFacade.findAllTopicsPageByPage(pageRequest);
    }
}
