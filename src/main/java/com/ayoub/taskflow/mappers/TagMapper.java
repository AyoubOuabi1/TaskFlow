package com.ayoub.taskflow.mappers;

import com.ayoub.taskflow.dto.TagDTO;
import com.ayoub.taskflow.entities.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TagMapper  {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);
    Tag  mapToTag(TagDTO tagDTO);

    TagDTO fromEntity(Tag tag);
}
