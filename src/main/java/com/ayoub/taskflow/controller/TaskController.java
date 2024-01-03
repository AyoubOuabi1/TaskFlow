package com.ayoub.taskflow.controller;
import com.ayoub.taskflow.dto.TaskDTO;
import com.ayoub.taskflow.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        List<TaskDTO> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long taskId)  {
        TaskDTO task = taskService.getTaskById(taskId);
        return ResponseEntity.ok(task);
    }

    @PostMapping("/{currentUserId}")
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDto ,@PathVariable Long currentUserId) {
        TaskDTO createdTask = taskService.createTask(taskDto, currentUserId);
        return ResponseEntity.ok(createdTask);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long taskId, @RequestBody TaskDTO taskDto) {
        TaskDTO updateTask = taskService.updateTask(taskId, taskDto);
        return ResponseEntity.ok(updateTask);
    }

    @DeleteMapping("/{taskId}/{currentUserId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId,@PathVariable Long currentUserId) {
        taskService.deleteTask(taskId,currentUserId);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{taskId}/complete")
    public ResponseEntity<TaskDTO> completeTask(@PathVariable Long taskId) {
        TaskDTO completedTask = taskService.completeTask(taskId);
        return ResponseEntity.ok(completedTask);
    }
}
