package com.ayoub.taskflow.mapper;

import com.ayoub.taskflow.dto.RequestDTO;
import com.ayoub.taskflow.entities.Request;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UserMapper.class, TaskMapper.class})
public interface RequestMapper {
    RequestMapper INSTANCE= Mappers.getMapper(RequestMapper.class);
    //@Mapping(target = "newAssigneeId", source = "newAssignee")

    RequestDTO toRequestDTO(Request request);



    Request toRequest(RequestDTO requestDTO);
}
