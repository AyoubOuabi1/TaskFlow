package com.ayoub.taskflow.repository;

import com.ayoub.taskflow.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
