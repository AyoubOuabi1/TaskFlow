package com.ayoub.taskflow.dto;

import com.ayoub.taskflow.entities.enums.Role;
import jakarta.validation.constraints.Null;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String username;
    private Role role;
    @Null
    private Set<TaskDTO> assignedTasks;
}
