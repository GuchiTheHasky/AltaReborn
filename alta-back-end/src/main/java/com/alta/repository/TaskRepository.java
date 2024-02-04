package com.alta.repository;

import com.alta.dto.TopicDto;
import com.alta.entity.Task;
import com.alta.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

//    @Query("SELECT t FROM Task t WHERE t.topic.id IN :topicIds")
//    List<Task> findByTopicIds(@Param("topicIds") List<Integer> topicIds);

    @Query("SELECT t FROM Task t WHERE t.id IN :taskIds")
    List<Task> findAllByIds(List<Integer> taskIds);

    @Query("SELECT t FROM Task t WHERE t.topic IN :topics")
    List<Task> findAllTasksIncludedInTopics(List<Topic> topics);

    @Query("SELECT t FROM Task t WHERE t.title IN :title")
    List<Task> findAllByTitle(String title);
}
