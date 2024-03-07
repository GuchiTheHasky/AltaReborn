package com.alta.service;

import com.alta.dto.TopicDto;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * Service interface for managing {@link com.alta.entity.Topic} entities.
 */
public interface TopicService {

    List<TopicDto> findAll(Integer page, Integer size);
}
