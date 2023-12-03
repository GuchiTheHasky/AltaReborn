package com.alta.service.impl;

import com.alta.entity.Topic;
import com.alta.exception.TopicException;
import com.alta.repository.TopicRepository;
import com.alta.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultTopicService implements TopicService {
    private final TopicRepository topicRepository;

    @Override
    public List<Topic> findAll() {
        return topicRepository.findAll();
    }

    @Override
    public Topic save(Topic topic) {
        return topicRepository.save(topic);
    }

    @Override
    public void delete(int id) {
        topicRepository.findById(id).ifPresent(topic -> topicRepository.deleteById(id));
    }

    @Override
    public Topic update(int id, Topic topic) {
        return topicRepository.findById(id)
                .map(topicRequired -> {
                    topicRequired.setName(topic.getName());
                    topicRequired.setSubtopics(topic.getSubtopics());
                    return topicRepository.save(topicRequired);
                })
                .orElseThrow(() -> new TopicException(id));
    }
}
