package com.ayoub.taskflow.service.serviceImpl;

import com.ayoub.taskflow.dto.RequestDTO;
import com.ayoub.taskflow.dto.TaskDTO;
import com.ayoub.taskflow.dto.UserDTO;
import com.ayoub.taskflow.entities.Request;
import com.ayoub.taskflow.entities.Task;
import com.ayoub.taskflow.entities.enums.RequestStatus;
import com.ayoub.taskflow.entities.enums.RequestType;
import com.ayoub.taskflow.exception.InvalidDateRangeException;
import com.ayoub.taskflow.repository.RequestRepository;
import com.ayoub.taskflow.repository.TaskRepository;
import com.ayoub.taskflow.service.UserService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RequestServiceImplTest {
    @Mock
    private RequestRepository requestRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    TaskServiceImpl taskService;

    @Mock
    private UserService userService;



    @InjectMocks
    private RequestServiceImpl requestService;


    @Test
    public void testCreateRequest() {

       // RequestDTO requestDTO = new RequestDTO();

        UserDTO userDTO = new UserDTO();

        Task oldTask = new Task();
        when(userService.getUserById(anyLong())).thenReturn(userDTO);
        when(taskService.getTaskById(anyLong())).thenReturn(modelMapper.map(oldTask, TaskDTO.class));
        when(requestRepository.save(any())).thenReturn(new Request());

        //RequestDTO result = requestService.createRequest(requestDTO);

        verify(userService, times(1)).getUserById(anyLong());
        verify(taskService, times(1)).getTaskById(anyLong());
        verify(requestRepository, times(1)).save(any());
    }

    @Test
    public void testCreateRequest_TaskReplacementExceededTokens() {
        // Arrange
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setCreatedById(1L);
        requestDTO.setOldTaskId(2L);
        requestDTO.setRequestType(RequestType.TASK_REPLACEMENT);
        requestDTO.setRequestStatus(RequestStatus.PENDING);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);

        Task oldTask = new Task();
        oldTask.setId(2L);

        when(userService.getUserById(1L)).thenReturn(userDTO);
        when(taskService.getTaskById(2L)).thenReturn(modelMapper.map(oldTask, TaskDTO.class));
        when(requestRepository.countBy(1L, LocalDate.now(), RequestType.TASK_REPLACEMENT)).thenReturn(2L);

        assertThrows(InvalidDateRangeException.class, () -> requestService.createRequest(requestDTO));

        verify(userService, times(1)).getUserById(1L);
        verify(taskService, times(1)).getTaskById(2L);
        verify(requestRepository, times(0)).save(any());
    }

}