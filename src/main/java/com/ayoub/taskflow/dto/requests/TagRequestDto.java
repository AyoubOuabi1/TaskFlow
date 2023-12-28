package com.ayoub.taskflow.dto.requests;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class TagRequestDto {
    @NotNull(message = "Name is required")
    String name;
}
