package com.alta.service.impl;

import com.alta.dto.TopicDto;
import com.alta.mapper.TopicMapper;
import com.alta.repository.TopicRepository;
import com.alta.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultTopicService implements TopicService {
    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    @Override
    public List<TopicDto> findAll() {
        return topicRepository.findAll().stream()
                .map(topicMapper::toTopicDto)
                .toList();
    }
}
