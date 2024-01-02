package com.ayoub.taskflow.service.serviceImpl;

import com.ayoub.taskflow.dto.RequestDTO;
import com.ayoub.taskflow.dto.UserDTO;
import com.ayoub.taskflow.entities.enums.RequestStatus;
import com.ayoub.taskflow.entities.enums.RequestType;
import com.ayoub.taskflow.exception.InvalidDateRangeException;
import com.ayoub.taskflow.entities.*;
import com.ayoub.taskflow.repository.RequestRepository;
import com.ayoub.taskflow.repository.TaskRepository;
import com.ayoub.taskflow.service.RequestService;
import com.ayoub.taskflow.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    ModelMapper modelMapper;
    private final UserService userService;
    private final TaskRepository taskRepository;

     public RequestServiceImpl(RequestRepository requestRepository,
                               ModelMapper modelMapper,
                               UserService userService,
                               TaskRepository taskRepository) {
        this.requestRepository = requestRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.taskRepository = taskRepository;
     }


    @Override
    public RequestDTO createRequest(RequestDTO requestDTO) {
        Long createdById = requestDTO.getCreatedById();
        Long oldTaskId = requestDTO.getOldTaskId();
        RequestType requestType = requestDTO.getRequestType();
        RequestStatus requestStatus = requestDTO.getRequestStatus();

        UserDTO createdBy = userService.getUserById(createdById);
        Task oldTask = taskRepository.findById(oldTaskId)
                .orElseThrow(() -> new InvalidDateRangeException("Task not found with id: " + oldTaskId));


        validateTokenUsage(createdById, requestType);

        requestDTO.setCreatedById(createdById);
        requestDTO.setOldTaskId(oldTaskId);
        requestDTO.setRequestStatus(requestStatus);
        requestDTO.setRequestType(requestType);
        requestDTO.setRequestDate(LocalDate.now());

        Request request = modelMapper.map(requestDTO, Request.class);


        request.setCreatedBy(modelMapper.map(createdBy, User.class));
        request.setOldTask(oldTask);

        request.setRequestDate(LocalDate.now());
        request.setRequestStatus(requestStatus);
        request.setRequestType(requestType);

        Request savedRequest = requestRepository.save(request);
        return modelMapper.map(savedRequest,RequestDTO.class);
    }

    private void validateTokenUsage(Long createdById, RequestType requestType) {

        long countDay = requestRepository.countBycreatedByIdAndRequestDateAndRequestType(createdById, LocalDate.now(), RequestType.TASK_REPLACEMENT);

        YearMonth yearMonth = YearMonth.now();
        LocalDate startOfMonth = yearMonth.atDay(1);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();


        Long monthlyDeletion = requestRepository.countBycreatedByIdAndRequestDateBetweenAndRequestType(
                createdById, startOfMonth, endOfMonth, RequestType.TASK_DELETED
        );

        if (RequestType.TASK_REPLACEMENT.equals(requestType)) {

            if (countDay >= 2) {
                throw new InvalidDateRangeException("You dont have replacement tokens for today");
            }
        } else if (RequestType.TASK_DELETED.equals(requestType)) {

            if (monthlyDeletion >= 1) {
                throw new InvalidDateRangeException("You dont have deletion tokens for this month");
            }
        }
    }
}
