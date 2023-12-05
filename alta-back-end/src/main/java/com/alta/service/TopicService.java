package com.alta.service;

import com.alta.entity.Topic;

import java.util.List;

public interface TopicService {
    List<Topic> findAll();

    Topic save(Topic topic);

    void delete(int id);

    Topic update(int id, Topic topic);
}
