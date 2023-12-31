package com.ayoub.taskflow.dto;

import com.ayoub.taskflow.entities.enums.Role;
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
    private Set<TaskDTO> assignedTasks;
}
