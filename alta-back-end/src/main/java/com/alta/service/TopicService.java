package com.alta.service;

import com.alta.dto.TopicDto;
import java.util.List;

/**
 * Interface for managing Topic data.
 * Provides functionality to retrieve Topic objects.
 */
public interface TopicService {
    /**
     * Retrieves a list of all available Topic objects.
     * @return A list of TopicDto objects representing the available topics.
     */
    List<TopicDto> findAll();
}
