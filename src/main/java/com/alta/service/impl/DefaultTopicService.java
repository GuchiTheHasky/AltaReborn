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
    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    @Override
    public List<TopicDto> findAll() {
        return topicRepository.findAll().stream().map(topicMapper::toTopicDto).collect(Collectors.toList());
    }

    @Override
    public TopicDto save(TopicDto topicDto) {
        Topic topicToSave = topicMapper.toTopic(topicDto);
        topicRepository.save(topicToSave);
        return topicMapper.toTopicDto(topicToSave);
    }

    @Override
    public void delete(int id) {
        topicRepository.findById(id).ifPresent(topic -> topicRepository.deleteById(id));
    }

    @Override
    public TopicDto update(int id, TopicDto topicDto) {
        return topicRepository.findById(id)
                .map(topicRequired -> {
                    topicRequired.setName(topicDto.getName());
                    topicRequired.setSubtopics(topicDto.getSubtopics());
                    topicRepository.save(topicRequired);
                    return topicMapper.toTopicDto(topicRequired);
                })
                .orElseThrow(() -> new TopicException(id));
    }
}
