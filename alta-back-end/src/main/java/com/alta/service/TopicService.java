package com.alta.service;

import com.alta.dto.TopicDto;
import com.alta.entity.Topic;
import java.util.List;

/**
 * Interface for managing the Topic data.
 * Provides functionality to return, save, update, and delete Topic objects.
 */

public interface TopicService {

    /**
     * Returns a list of all Topic objects available.
     *
     * @return A list of TopicDto objects representing the available topics.
     */
    List<TopicDto> findAll();

    /**
     * Saves a new Topic.
     *
     * @param topicDto : An object containing information about the topic to be saved.
     * @return An object representing the newly saved topic.
     */
    TopicDto save(TopicDto topicDto);

    /**
     * Deletes a Topic object based on its unique identifier.
     *
     * @param id : The unique identifier of the Topic object to be deleted.
     */
    void delete(int id);

    /**
     * Updates an existing Topic object with the provided information.
     *
     * @param id : The unique identifier of the Topic object to be updated.
     * @param topicDto : An object containing the updated information for the topic.
     * @return An object representing the updated topic.
     */
    TopicDto update(int id, TopicDto topicDto);

    Topic findById(int topicId);

    Topic findByName(String name);
}
