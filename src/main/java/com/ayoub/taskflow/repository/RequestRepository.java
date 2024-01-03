package com.ayoub.taskflow.repository;

import com.ayoub.taskflow.entities.Request;
import com.ayoub.taskflow.entities.enums.RequestType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface RequestRepository extends JpaRepository<Request, Long> {

    @Query("select count (r) from Request r where r.createdBy= :userId and r.requestDate= :requestDate and r.requestType = :requestType")
    long countDay(Long userId, LocalDate requestDate, RequestType requestType);
    long countBycreatedByIdAndRequestDateBetweenAndRequestType(
            Long userId,
            LocalDate startOfMonth,
            LocalDate endOfMonth,
            RequestType requestType
    );
}
