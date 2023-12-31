package com.ayoub.taskflow.mapper;

import com.ayoub.taskflow.dto.TaskDTO;
import com.ayoub.taskflow.entities.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface TaskMapper {
    TaskMapper INSTANCE= Mappers.getMapper(TaskMapper.class);

    TaskDTO toTaskDto(Task task);


    Task toTask(TaskDTO taskDto);
}
