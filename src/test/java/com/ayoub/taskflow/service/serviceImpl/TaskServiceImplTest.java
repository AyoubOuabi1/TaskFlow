package com.ayoub.taskflow.service.serviceImpl;

import com.ayoub.taskflow.dto.TaskDTO;
import com.ayoub.taskflow.repository.TaskRepository;
import com.ayoub.taskflow.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    TaskRepository taskRepository;
    @InjectMocks
    TaskService taskService;

    @Test
    void createTask() {

        TaskDTO taskDTO = TaskDTO.builder()
                .createdById(1L)
                .build();
    }
}