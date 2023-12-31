package com.ayoub.taskflow.service;


import com.ayoub.taskflow.dto.TaskDTO;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface TaskService {
    List<TaskDTO> getAllTasks();

    TaskDTO getTaskById(Long taskId);

    TaskDTO createTask(TaskDTO taskDto, Long currentUserId);

    TaskDTO updateTask(Long taskId, TaskDTO taskDto);

    void deleteTask(Long taskId , Long currentUserId);
    TaskDTO completeTask(Long taskId);
}
