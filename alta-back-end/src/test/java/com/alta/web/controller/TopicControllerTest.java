package com.alta.web.controller;

import com.alta.dto.TopicDto;
import com.alta.service.TopicService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Log4j2
class TopicControllerTest {

    @Mock
    private TopicService topicService;

    @InjectMocks
    private TopicController topicController;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Unit test findAll() for TopicDto, check size and values")
    void findAll_ReturnsListOfTopicsDto() {
        log.info("Running findAll_ReturnsListOfTopicsDto test...");

        List<TopicDto> topics = new ArrayList<>();
        topics.add(new TopicDto(1, "Topic 1"));
        topics.add(new TopicDto(2, "Topic 2"));

        when(topicService.findAll(null, null)).thenReturn(topics);

        List<TopicDto> result = topicController.findAll(null, null);

        verify(topicService).findAll(null, null);

        assertEquals(2, result.size());

        assertEquals(topics.get(0).getId(), result.get(0).getId());
        assertEquals(topics.get(0).getTitle(), result.get(0).getTitle());

        assertEquals(topics.get(1).getId(), result.get(1).getId());
        assertEquals(topics.get(1).getTitle(), result.get(1).getTitle());
    }


    @Test
    @DisplayName("Unit test findAll() for TopicDto with pagination, check size and values")
    void findAll_ReturnsListOfTopicsDto_WithPagination() {
        log.info("Running findAll_ReturnsListOfTopicsDto_WithPagination test...");

        int page = 1;
        int size = 5;

        List<TopicDto> topics = new ArrayList<>();
        topics.add(new TopicDto(3, "Topic 3"));
        topics.add(new TopicDto(4, "Topic 4"));

        when(topicService.findAll(page, size)).thenReturn(topics);

        List<TopicDto> result = topicController.findAll(page, size);

        verify(topicService).findAll(page, size);

        assertEquals(2, result.size());

        assertEquals(topics.get(0).getId(), result.get(0).getId());
        assertEquals(topics.get(0).getTitle(), result.get(0).getTitle());

        assertEquals(topics.get(1).getId(), result.get(1).getId());
        assertEquals(topics.get(1).getTitle(), result.get(1).getTitle());
    }
}
