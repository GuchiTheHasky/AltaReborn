package com.alta.web.controller;

import com.alta.dto.TopicDto;
import com.alta.facade.MainFacade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class TopicControllerTest {
    @Mock
    private MainFacade mainFacade;

    @InjectMocks
    private TopicController topicController;

    @Test
    @DisplayName("Unit test findAll() for TopicDto, check size and values")
    void findAll_ReturnsListOfTopics() {

        List<TopicDto> topics = new ArrayList<>();
        topics.add(new TopicDto(1, "Topic 1"));
        topics.add(new TopicDto(2, "Topic 2"));

        when(mainFacade.findAllTopics()).thenReturn(topics);

        List<TopicDto> result = topicController.findAll();

        verify(mainFacade).findAllTopics();

        assertEquals(2, result.size());
        assertEquals(topics.get(0), result.get(0));
        assertEquals(topics.get(1), result.get(1));
    }

    @Test
    @DisplayName("Unit test findAllTopicsPageByPage() for TopicDto, check size and values")
    void findAllTopicsPageByPage_ReturnsListOfTopics() {

        PageRequest pageRequest = PageRequest.of(0, 15);

        List<TopicDto> topics = new ArrayList<>();
        topics.add(new TopicDto(1, "Topic 1"));
        topics.add(new TopicDto(2, "Topic 2"));

        when(mainFacade.findAllTopicsPageByPage(pageRequest)).thenReturn(topics);

        List<TopicDto> result = topicController.findAllTopicsPageByPage(0, 15);

        verify(mainFacade).findAllTopicsPageByPage(pageRequest);

        assertEquals(2, result.size());
        assertEquals(topics.get(0), result.get(0));
        assertEquals(topics.get(1), result.get(1));
    }
}