package com.ayoub.taskflow.mapper;

import com.ayoub.taskflow.dto.UserDTO;
import com.ayoub.taskflow.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE= Mappers.getMapper(UserMapper.class);
    UserDTO entityToDto(User user);

    User dtoToEntity(UserDTO userDTO);
}
