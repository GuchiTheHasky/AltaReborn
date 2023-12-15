package com.alta.repository;

import com.alta.dto.TopicDto;
import com.alta.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Integer> {
    Topic findByName(String name);
}
