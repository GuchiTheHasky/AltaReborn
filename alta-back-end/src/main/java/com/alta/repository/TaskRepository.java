package com.alta.repository;

import com.alta.entity.Task;
import com.alta.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query("SELECT t FROM Task t WHERE t.topic IN :topics")
    List<Task> findAllTasksIncludedInTopics(List<Topic> topics);
}
