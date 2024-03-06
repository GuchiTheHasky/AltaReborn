package com.alta.repository;

import com.alta.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

/**
 * Repository interface for managing {@link Task} entities.
 * Extends JpaRepository for standard CRUD operations.
 */
public interface TaskRepository extends JpaRepository<Task, Integer> {
/*
* select t.id, top.id, et.exam_id, student_id from task t
left join public.topic top on t.topic_id = top.id
left join public.exam_tasks et on t.id = et.task_id
left join public.exam_student es on et.exam_id = es.exam_id
where topic_id in (20, 54)*/
    //@Query(value = "select t from Task t left join exam_tasks et on t.id = et.task_id left join exam_student es on et.exam_id = es.exam_id where t.topic_id in :topicIds", nativeQuery = true)
    @Query(value = "select t.id, t.image_path, t.level, t.title, t.answer, t.topic_id from Task t " +
                   "left join exam_tasks et on t.id = et.task_id " +
                   "left join exam_student es on et.exam_id = es.exam_id where t.topic_id in :topicIds " +
                   "and es.student_id in :studentIds", nativeQuery = true)
    Set<Task> findTasksAtLeastOneStudentAttempted(@Param("studentIds") List<Integer> studentIds, @Param("topicIds") List<Integer> topicIds);

    @Query(value = "select t.id, t.image_path, t.level, t.title, t.answer, t.topic_id from Task t " +
            "where t.topic_id IN :topics and t.id not in :ids", nativeQuery = true)
    Set<Task> findNotAttemptedTasks(List<Integer> ids, List<Integer> topics);


    /**
     * Retrieves a list of tasks based on their topic IDs.
     *
     * @param topics A list of topic IDs to filter tasks by.
     * @return A list of {@link Task} entities associated with the specified topic IDs.
     */
    @Query("SELECT t FROM Task t WHERE t.topic.id IN :topics")
    List<Task> findByTopicIds(List<Integer> topics);
}
