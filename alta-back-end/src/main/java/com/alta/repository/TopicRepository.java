package com.alta.repository;

import com.alta.entity.Topic;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing {@link Topic} entities.
 * Extends JpaRepository for standard CRUD operations.
 */
@NonNullApi
public interface TopicRepository extends JpaRepository<Topic, Integer> {

    /**
     * Finds a Topic by its title.
     *
     * @param title The title of the Topic.
     * @return An Optional containing the Topic if found, or empty otherwise.
     */
    Optional<Topic> findByTitle(String title);

    /**
     * Retrieves a page of topics.
     *
     * @param pageable Object defining the page to retrieve.
     * @return A page of topics.
     */
    Page<Topic> findAll(Pageable pageable);
}
