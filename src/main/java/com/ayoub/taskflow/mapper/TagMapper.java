package com.ayoub.taskflow.mapper;
import com.ayoub.taskflow.dto.TagDTO;
import com.ayoub.taskflow.entities.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface TagMapper {
    TagMapper INSTANCE= Mappers.getMapper(TagMapper.class);
    TagDTO toTagDTO(Tag tag);
    Tag toTag(TagDTO tagDTO);
}