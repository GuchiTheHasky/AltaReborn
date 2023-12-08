package com.alta.repository;

import com.alta.entity.Task;
import com.alta.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByTopic(Topic topic);

    List<Task> findAllByStudentsId(int id);
}
