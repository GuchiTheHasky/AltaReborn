package com.alta.service;

import com.alta.dto.TopicDto;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * Service interface for managing {@link com.alta.entity.Topic} entities.
 */
public interface TopicService {
    /**
     * Retrieves a list of all available Topic objects.
     *
     * @return A list of TopicDto objects representing the available topics.
     */
    List<TopicDto> findAll();

    List<TopicDto> findAllTopicsPageByPage(PageRequest pageRequest);
}
