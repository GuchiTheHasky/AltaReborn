package com.alta.repository;

import com.alta.entity.Student;
import com.alta.entity.Task;
import com.alta.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
//    @Query("SELECT t FROM Task t WHERE t.topic IN :topics")
//    List<Task> findAllTasksIncludedInTopics(List<Topic> topics);
//
//    @Query(value = """
//            SELECT t.id, t.image_path, t.level, t.answer, t.title, t.status, t.topic_id, t.text
//             FROM task t
//             WHERE t.topic_id IN (:topicIds)
//             AND t.id NOT IN (
//                 SELECT st.task_id
//                 FROM student_task st
//                 WHERE st.student_id IN (:studentIds)
//                 GROUP BY st.task_id
//                 HAVING COUNT(st.task_id) = (SELECT COUNT(*) FROM students WHERE id IN (:studentIds)));""", nativeQuery = true)
//    List<Task> findAllByTopicsAndStudents(List<Integer> topicIds, List<Integer> studentIds);

    @Query(value = """
       SELECT t.id, t.image_path, t.level, t.answer, t.title, t.status, t.topic_id, t.text
       FROM task t
       WHERE t.topic_id IN (:topicIds)
       AND  t.status IS NULL
       """, nativeQuery = true)
    List<Task> findAllTasks(@Param("topicIds") List<Integer> topicIds);

//    @Query(value = """
//       SELECT t.id, t.image_path, t.level, t.answer, t.title, t.status, t.topic_id, t.text
//       FROM task t
//       WHERE t.topic_id IN (:topicIds)
//       AND  t.status IS NULL
//       AND NOT EXISTS (
//           SELECT 1
//           FROM student_task st
//           WHERE st.task_id = t.id AND st.student_id IN (:studentIds)
//       )
//       """, nativeQuery = true)
//    List<Task> findAllTasks(@Param("topicIds") List<Integer> topicIds, @Param("studentIds") List<Integer> studentIds);
//

}
