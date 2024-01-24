package com.alta.repository;

import com.alta.entity.Topic;
import com.fasterxml.jackson.datatype.jdk8.Jdk8OptionalBeanPropertyWriter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullApi;

import java.util.List;
import java.util.Optional;


public interface TopicRepository extends JpaRepository<Topic, Integer> {

    @Query("SELECT t FROM Topic t WHERE t.id IN :topicsIds")
    List<Topic> findAllByIds(List<Integer> topicsIds);

    //@NonNull
    @EntityGraph(value = "topic-entity-graph", type = EntityGraph.EntityGraphType.FETCH)
    //@Query("SELECT t FROM Topic t JOIN fetch t.tasks")
    List<Topic> findAll();
    //@NonNull
    Page<Topic> findAll(/*@NonNull*/ Pageable pageable);


    @Query("SELECT t FROM Topic t WHERE t.title = :title")
    Optional<Topic> findByTitle(String title);
}
