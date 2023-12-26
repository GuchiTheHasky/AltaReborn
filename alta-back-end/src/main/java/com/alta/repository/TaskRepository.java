package com.alta.repository;

import com.alta.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
//    @Query("SELECT t FROM Task t WHERE t.topic.id = :id")
//    List<Task> findAllByTopicId(@Param("id") int topicId);

}
