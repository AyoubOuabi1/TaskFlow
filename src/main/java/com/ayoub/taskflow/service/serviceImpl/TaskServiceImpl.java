package com.ayoub.taskflow.service.serviceImpl;

import com.ayoub.taskflow.dto.TaskDTO;
import com.ayoub.taskflow.dto.UserDTO;
import com.ayoub.taskflow.entities.enums.Role;
import com.ayoub.taskflow.entities.enums.TaskStatus;
import com.ayoub.taskflow.exception.InvalidDateRangeException;
import com.ayoub.taskflow.exception.NotFoundException;
import com.ayoub.taskflow.entities.*;
import com.ayoub.taskflow.repository.TagRepository;
import com.ayoub.taskflow.repository.TaskRepository;
import com.ayoub.taskflow.repository.TaskTagRepository;
import com.ayoub.taskflow.service.TaskService;
import com.ayoub.taskflow.service.UserService;
import com.ayoub.taskflow.service.TagService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;

    private final TagService tagService;
    private final ModelMapper modelMapper;

    private  final TaskTagRepository taskTagRepository;

     public TaskServiceImpl(TaskRepository taskRepository,
                            ModelMapper modelMapper,
                            UserService userService,
                            TagService tagService,
                             TaskTagRepository taskTagRepository) {
        this.taskRepository = taskRepository;
         this.userService = userService;
        this.tagService = tagService;
         this.modelMapper = modelMapper;
        this.taskTagRepository = taskTagRepository;
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(task->modelMapper.map(task, TaskDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO getTaskById(Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        if(task.isPresent()){
            return modelMapper.map(task.get(), TaskDTO.class);

        }else {
            throw new NotFoundException("Task not found with id: " + taskId);
        }

    }

    @Override
    public TaskDTO createTask(TaskDTO taskDto, Long currentUserId) {
        validateAssigneeAndTagsExistence(taskDto);

        boolean isManager = userService.isUserManager(currentUserId);

        Task task = modelMapper.map(taskDto, Task.class);

        if (taskDto.getStartDate() != null) {
            LocalDate currentDate = LocalDate.now();
            LocalDate allowedStartDate = currentDate.plusDays(3);

            if (taskDto.getStartDate().isAfter(allowedStartDate)) {
                throw new InvalidDateRangeException("The start date must be before 3 days from the current day");
            }
        }
        if (taskDto.getStartDate() != null && taskDto.getEndDate() != null
                && taskDto.getEndDate().isBefore(taskDto.getStartDate())) {
            throw new InvalidDateRangeException("End date must be after start date");
        }

        if (taskDto.getAssigneeId() != null) {

            if (isManager || currentUserId.equals(taskDto.getAssigneeId())) {
                UserDTO user = userService.getUserById(taskDto.getAssigneeId());
                task.setAssignee(modelMapper.map(user, User.class));
            } else {
                throw new InvalidDateRangeException("You are not authorized to assign tasks to other users");
            }
        }

        // createdBy
        UserDTO createdBy = userService.getUserById(currentUserId);
        task.setCreatedBy(modelMapper.map(createdBy, User.class));

        Task savedTask = taskRepository.save(task);

        if (taskDto.getTagIds() != null && !taskDto.getTagIds().isEmpty()) {
            Set<Long> tagIds = taskDto.getTagIds();
            Set<Tag> existingTags = new HashSet<>(tagService.findAllTagsById(tagIds));

            Set<Long> nonExistingTagIds = tagIds.stream()
                    .filter(tagId -> existingTags.stream().noneMatch(tag -> tag.getId().equals(tagId)))
                    .collect(Collectors.toSet());

            if (!nonExistingTagIds.isEmpty()) {
                throw new NotFoundException("Tags not found with IDs: " + nonExistingTagIds);
            }
            Set<TaskTag> taskTags = existingTags.stream()
                    .map(tag -> TaskTag.builder().tag(tag).task(savedTask).build())
                    .collect(Collectors.toSet());

            taskTagRepository.saveAll(taskTags);
        }

        return modelMapper.map(savedTask, TaskDTO.class);
    }




    @Override
    public TaskDTO updateTask(Long taskId, TaskDTO taskDto) {
        return null;
    }

    @Override
    public void deleteTask(Long taskId, Long currentUserId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NotFoundException("Task not found with id: " + taskId));
        UserDTO loggedInUser = userService.getUserById(currentUserId);
        if (Objects.equals(currentUserId, task.getCreatedBy().getId())) {
            taskRepository.deleteById(taskId);
            System.out.println("deleted");
        } else if (loggedInUser.getRole() == Role.MANAGER) {
            taskRepository.deleteById(taskId);
            System.out.println("deleted");
        } else {
            throw new NotFoundException("You do not have permission to delete this task.");
        }
    }
    @Override
    public TaskDTO completeTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NotFoundException("Task not found with ID: " + taskId));

        if (task.getStatus() == TaskStatus.COMPLETED) {
            throw new NotFoundException("Task is already marked as completed");
        }

        LocalDate currentDate = LocalDate.now();
        if (task.getEndDate() != null && currentDate.isAfter(task.getEndDate())) {
            throw new NotFoundException("Task cannot be completed after the deadline");
        }

        task.setStatus(TaskStatus.COMPLETED);
        Task savedTask = taskRepository.save(task);

        return modelMapper.map(savedTask, TaskDTO.class);
    }

    private void validateAssigneeAndTagsExistence(TaskDTO taskDto) {
        if (taskDto.getAssigneeId() != null && !userService.existsById(taskDto.getAssigneeId())) {
            throw new NotFoundException("User not found with id: " + taskDto.getAssigneeId());
        }

        if (taskDto.getTagIds() != null) {
            for (Long tagId : taskDto.getTagIds()) {
                if (!tagService.existsById(tagId)) {
                    throw new NotFoundException("Tag not found with id: " + tagId);
                }
            }
        }
    }
}