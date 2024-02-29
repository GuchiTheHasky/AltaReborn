//package com.alta.web.controller;
//
//import com.alta.dto.StudentDto;
//import com.alta.dto.TopicDto;
//import com.alta.facade.MainFacade;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class TopicControllerTest {
//
//    @Mock
//    private MainFacade mainFacade;
//
//    @InjectMocks
//    private TopicController topicController;
//
//    @Autowired
//    private TestRestTemplate testRestTemplate;
//
//    @Test
//    @DisplayName("Integration test findAll() for TopicDto, check status code and content type ")
//    void findAll_shouldTReturnStatusOkAndContentTypeApplicationJson() {
//
//        ResponseEntity<List<TopicDto>> responseEntity = testRestTemplate.exchange(
//                "/api/v1/topics",
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<>() {});
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//
//        assertTrue(Objects.requireNonNull(responseEntity.getHeaders().getContentType())
//                .isCompatibleWith(MediaType.APPLICATION_JSON));
//    }
//
//    @Test
//    @DisplayName("Unit test findAll() for TopicDto, check size and values")
//    void findAll_ReturnsListOfTopics() {
//
//        List<TopicDto> topics = new ArrayList<>();
//        topics.add(new TopicDto(1, "Topic 1"));
//        topics.add(new TopicDto(2, "Topic 2"));
//
//        when(mainFacade.findAllTopics()).thenReturn(topics);
//
//        List<TopicDto> result = topicController.findAll();
//
//        verify(mainFacade).findAllTopics();
//
//        assertEquals(2, result.size());
//
//        assertEquals(topics.get(0).getId(), result.get(0).getId());
//        assertEquals(topics.get(0).getTitle(), result.get(0).getTitle());
//
//        assertEquals(topics.get(1).getId(), result.get(1).getId());
//        assertEquals(topics.get(1).getTitle(), result.get(1).getTitle());
//    }
//
//}