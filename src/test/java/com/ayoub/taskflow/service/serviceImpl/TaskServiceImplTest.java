package com.ayoub.taskflow.service.serviceImpl;

import com.ayoub.taskflow.dto.TaskDTO;
import com.ayoub.taskflow.exception.InvalidDateRangeException;
import com.ayoub.taskflow.repository.TaskRepository;
import com.ayoub.taskflow.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @InjectMocks
    private TaskServiceImpl taskService;
    @Test
    public void testValidateStartDate() {

        LocalDate validStartDate = LocalDate.now().plusDays(1);
        assertDoesNotThrow(() -> taskService.validateStartDate(validStartDate));

         LocalDate invalidStartDateBeforeToday = LocalDate.now().minusDays(1);
        InvalidDateRangeException exception1 = assertThrows(InvalidDateRangeException.class,
                () -> taskService.validateStartDate(invalidStartDateBeforeToday));
        assertEquals("The start date must be after the current date", exception1.getMessage());


         LocalDate invalidStartDateMoreThanThreeDaysInFuture = LocalDate.now().plusDays(5);
        InvalidDateRangeException exception2 = assertThrows(InvalidDateRangeException.class,
                () -> taskService.validateStartDate(invalidStartDateMoreThanThreeDaysInFuture));
        assertEquals("The start date must be before 3 days from the current day", exception2.getMessage());

        assertDoesNotThrow(() -> taskService.validateStartDate(null));
    }


}