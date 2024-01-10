package com.alta.repository;

import com.alta.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Integer> {

    @Query("SELECT t FROM Topic t WHERE t.id IN :topicsIds")
    List<Topic> findAllByIds(List<Integer> topicsIds);
}
