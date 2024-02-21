package com.alta.repository;

import com.alta.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing {@link Topic} entities.
 * Extends JpaRepository for standard CRUD operations.
 */
public interface TopicRepository extends JpaRepository<Topic, Integer> {

    /**
     * Finds a Topic by its title.
     *
     * @param title The title of the Topic.
     * @return An Optional containing the Topic if found, or empty otherwise.
     */
    Optional<Topic> findByTitle(String title);
}
