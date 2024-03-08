package com.alta.service;

import com.alta.dto.TaskDto;
import com.alta.dto.TopicDto;
import com.alta.entity.Topic;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * Service interface for managing {@link com.alta.entity.Topic} entities.
 */
public interface TopicService {

    List<TopicDto> findAll(Integer page, Integer size);

    Topic findByTitle(TaskDto taskDto);
}
