package com.ayoub.taskflow.dto;

import com.ayoub.taskflow.entities.enums.TaskStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private TaskStatus status;
    private Long assigneeId;
    private Long createdById;
    private Set<Long> tagIds = new HashSet<>();
}
