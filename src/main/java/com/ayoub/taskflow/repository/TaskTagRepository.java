package com.ayoub.taskflow.repository;

import com.ayoub.taskflow.entities.TaskTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTagRepository extends JpaRepository<TaskTag, Long>{
}
