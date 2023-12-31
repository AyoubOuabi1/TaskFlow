package com.ayoub.taskflow.repository;

import com.ayoub.taskflow.entities.Request;
import com.ayoub.taskflow.entities.enums.RequestType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface RequestRepository extends JpaRepository<Request, Long> {
    long countBycreatedByIdAndRequestDateAndRequestType(Long userId, LocalDate requestDate, RequestType requestType);
    long countBycreatedByIdAndRequestDateBetweenAndRequestType(
            Long userId,
            LocalDate startOfMonth,
            LocalDate endOfMonth,
            RequestType requestType
    );
}
