package com.ayoub.taskflow.dto;

import com.ayoub.taskflow.entities.enums.RequestStatus;
import com.ayoub.taskflow.entities.enums.RequestType;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RequestDTO {
    private Long id;
    private Long newAssigneeId;
    private Long createdById;
    private Long oldTaskId;
    private Long newTaskId;
    private RequestStatus requestStatus;
    private RequestType requestType;
    private LocalDate requestDate;
}
