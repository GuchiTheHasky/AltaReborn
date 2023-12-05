package com.alta.service;

import com.alta.dto.TopicDto;

import java.util.List;

public interface TopicService {
    List<TopicDto> findAll();

    TopicDto save(TopicDto topicDto);

    void delete(int id);

    TopicDto update(int id, TopicDto topicDto);
}
