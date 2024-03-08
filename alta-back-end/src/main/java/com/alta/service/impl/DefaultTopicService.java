package com.alta.service.impl;

import com.alta.dto.TaskDto;
import com.alta.dto.TopicDto;
import com.alta.entity.Topic;
import com.alta.exception.TopicException;
import com.alta.mapper.TopicMapper;
import com.alta.repository.TopicRepository;
import com.alta.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultTopicService implements TopicService {
    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    @Override
    public List<TopicDto> findAll(Integer page, Integer size) {
        return Optional.ofNullable(page).isEmpty() || Optional.ofNullable(size).isEmpty() ? findAllTopics() : findAllTopics(page, size);
    }

    @Override
    public Topic findByTitle(TaskDto taskDto) {
        if (taskDto == null) {
            throw new TopicException();
        }
        return topicRepository.findByTitle(taskDto.getTitle())
                .orElseThrow(() -> new TopicException(taskDto.getTitle()));
    }

    private List<TopicDto> findAllTopics() {
        return topicRepository.findAll().stream()
                .map(topicMapper::toTopicDto)
                .toList();
    }

    private List<TopicDto> findAllTopics(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Topic> topicsPage = topicRepository.findAll(pageable);

        return topicsPage.getContent().stream()
                .map(topicMapper::toTopicDto)
                .toList();
    }
}
