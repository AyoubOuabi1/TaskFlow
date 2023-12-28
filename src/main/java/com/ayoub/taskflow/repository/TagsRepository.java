package com.ayoub.taskflow.repository;

import com.ayoub.taskflow.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagsRepository extends JpaRepository<Tag, Long> {

}
