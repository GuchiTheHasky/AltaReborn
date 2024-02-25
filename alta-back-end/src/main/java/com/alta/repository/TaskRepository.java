package com.alta.repository;

import com.alta.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repository interface for managing {@link Task} entities.
 * Extends JpaRepository for standard CRUD operations.
 */
public interface TaskRepository extends JpaRepository<Task, Integer> {

    /**
     * Retrieves a list of tasks based on their topic IDs.
     *
     * @param topics A list of topic IDs to filter tasks by.
     * @return A list of {@link Task} entities associated with the specified topic IDs.
     */
    @Query("SELECT t FROM Task t WHERE t.topic.id IN :topics")
    List<Task> findByTopicIds(List<Integer> topics);
}
