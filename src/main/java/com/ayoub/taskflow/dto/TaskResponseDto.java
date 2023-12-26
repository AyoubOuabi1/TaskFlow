package com.ayoub.taskflow.services.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;


@Data
public class TaskResponseDto{
    private String title;
    private  String description;
    private  LocalDate creationDate;
    private  LocalDate dueDate;

}
