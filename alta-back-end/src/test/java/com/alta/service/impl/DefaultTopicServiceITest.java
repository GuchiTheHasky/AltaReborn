package com.alta.service.impl;

import com.alta.dto.TaskDto;
import com.alta.entity.Topic;
import com.alta.exception.TopicException;
import com.alta.service.TopicService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Testcontainers
@ActiveProfiles(profiles = "test")
@SqlGroup({
        @Sql(scripts = "classpath:db/init_topics_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = "classpath:db/clean_up_topics_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
class DefaultTopicServiceITest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15.3");

    @Autowired
    private TopicService topicService;

    @Test
    @DisplayName("Test - should return topic by title.")
    void shouldReturnTopicByTitle() {
        String expectedTitle = "Подільність";
        TaskDto taskDto = TaskDto.builder()
                .title(expectedTitle)
                .level("0")
                .imagePath("/pathToImage")
                .build();

        Topic topic = topicService.findByTitle(taskDto);

        Assertions.assertNotNull(topic);
        Assertions.assertEquals(expectedTitle, topic.getTitle());
    }

    @Test
    @DisplayName("Test - should throw exception if title doesn't exist.")
    void shouldThrowExceptionIfTitleDoesntExist() {
        String expectedExMessage = "Topic with specified title {Фізкультура} not found. Check the request details.";
        String title = "Фізкультура";
        TaskDto taskDto = TaskDto.builder()
                .title(title)
                .level("0")
                .imagePath("/pathToImage")
                .build();

        Throwable thrown = assertThrows(TopicException.class, () -> topicService.findByTitle(taskDto));
        assertEquals(thrown.getMessage(), expectedExMessage);
    }

    @Test
    @DisplayName("Test - should throw exception if parameter is null.")
    void shouldThrowExceptionIfParameterIsNull() {
        String expectedExMessage = "Couldn't find ant topics, cause title is null";

        Throwable thrown = assertThrows(TopicException.class, () -> topicService.findByTitle(null));
        assertEquals(thrown.getMessage(), expectedExMessage);
    }

}
