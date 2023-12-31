package com.ayoub.taskflow.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TagDTO {
    private Long id;
    private String name;
}