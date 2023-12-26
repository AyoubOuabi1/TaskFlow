package com.ayoub.taskflow.services.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.tasktrak.entities.User}
 */
public record UserDto(Long id, String username, boolean isManager) implements Serializable {
}