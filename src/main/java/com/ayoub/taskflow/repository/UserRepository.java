package com.ayoub.taskflow.repository;


import com.ayoub.taskflow.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsById(Long userId);
}
