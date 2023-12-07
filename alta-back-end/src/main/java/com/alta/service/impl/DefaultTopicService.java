package com.alta.service.impl;

import com.alta.dto.TaskDto;
import com.alta.dto.TopicDto;
import com.alta.entity.Task;
import com.alta.entity.Topic;
import com.alta.exception.TopicException;
import com.alta.mapper.TaskMapper;
import com.alta.mapper.TopicMapper;
import com.alta.repository.TopicRepository;
import com.alta.service.TaskService;
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
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @Override
    public List<TopicDto> findAll() {
        return topicRepository.findAll().stream().map(topicMapper::toTopicDto).collect(Collectors.toList());
    }

    @Override
    public TopicDto save(TopicDto topicDto) {
        Topic newTopic = topicMapper.toTopic(topicDto);
        return topicMapper.toTopicDto(topicRepository.save(newTopic));
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
                    return topicMapper.toTopicDto(topicRepository.save(topicRequired));
                })
                .orElseThrow(() -> new TopicException(id));
    }

    @Override
    public Topic findById(int topicId) {
        return topicRepository.findById(topicId).orElseThrow(() -> new TopicException(topicId));
    }

    @Override
    public TopicDto addTaskToTopic(int topicId, int taskId) {
        Topic topic = findById(topicId);
        Task task = taskService.findById(taskId);
        topic.addTask(task);
        return topicMapper.toTopicDto(topic);
    }

    @Override
    public List<TaskDto> getTasksByTopicId(int topicId) {
        Topic topic = findById(topicId);
        return topic.getTasks().stream().map(taskMapper::toTaskDto).collect(Collectors.toList());
    }
}
