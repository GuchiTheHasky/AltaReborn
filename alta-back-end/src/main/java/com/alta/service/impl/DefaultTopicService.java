package com.alta.service.impl;

import com.alta.dto.TopicDto;
import com.alta.entity.Topic;
import com.alta.exception.TopicException;
import com.alta.mapper.TopicMapper;
import com.alta.repository.TopicRepository;
import com.alta.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultTopicService implements TopicService {
    private final TopicRepository TOPIC_REPOSITORY;
    private final TopicMapper TOPIC_MAPPER;


    @Override
    public List<TopicDto> findAll() {
        return TOPIC_REPOSITORY.findAll().stream().map(TOPIC_MAPPER::toTopicDto).collect(Collectors.toList());
    }

    @Override
    public TopicDto save(TopicDto topicDto) {
        Topic newTopic = TOPIC_MAPPER.toTopic(topicDto);
        return TOPIC_MAPPER.toTopicDto(TOPIC_REPOSITORY.save(newTopic));
    }

    @Override
    public void delete(int id) {
        TOPIC_REPOSITORY.findById(id).ifPresent(topic -> TOPIC_REPOSITORY.deleteById(id));
    }

    @Override
    public TopicDto update(int id, TopicDto topicDto) {
        return TOPIC_REPOSITORY.findById(id)
                .map(topicRequired -> {
                    topicRequired.setName(topicDto.getName());
                    topicRequired.setSubtopics(topicDto.getSubtopics());
                    return TOPIC_MAPPER.toTopicDto(TOPIC_REPOSITORY.save(topicRequired));
                })
                .orElseThrow(() -> new TopicException(id));
    }

    @Override
    public Topic findById(int topicId) {
        return TOPIC_REPOSITORY.findById(topicId).orElseThrow(() -> new TopicException(topicId));
    }
}
