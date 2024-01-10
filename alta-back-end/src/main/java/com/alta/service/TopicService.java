package com.alta.service;

import com.alta.dto.TopicDto;
import java.util.List;

/**
 * Interface for managing the Topic data.
 * Provides functionality to return, save, update, and delete Topic objects.
 */

public interface TopicService {

    /**
     * Returns a list of all Topic objects available.
     * @return A list of TopicDto objects representing the available topics.
     */
    List<TopicDto> findAll();
}
