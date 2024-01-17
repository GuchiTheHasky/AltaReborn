package com.alta.service;

import com.alta.dto.TopicDto;
import com.alta.entity.Topic;
import java.util.List;

/**
 * Interface for managing the Topic data.
 * Provides functionality to return Topic objects.
 */

public interface TopicService {

    /**
     * Returns a list of all Topic objects available.
     * @return A list of TopicDto objects representing the available topics.
     */
    List<TopicDto> findAll();

    /**
     * Fetches a list of Topic objects based on their IDs.
     * @param topicsIds : List of unique identifiers of Topic objects to be found.
     */
    List<TopicDto> findAllByIds(List<Integer> topicsIds);

    Topic findById(Integer topicId);
}
