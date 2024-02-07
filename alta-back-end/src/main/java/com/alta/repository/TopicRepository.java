package com.alta.repository;

import com.alta.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Integer> {
    Page<Topic> findAll(/*@NonNull*/ Pageable pageable);

    Optional<Topic> findByTitle(String title);
}
