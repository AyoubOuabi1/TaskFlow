package com.ayoub.taskflow.service;

import com.ayoub.taskflow.dto.requests.TagRequestDto;
import com.ayoub.taskflow.dto.responses.TagResponseDto;

import java.util.List;

public interface TagsService {

    List<TagResponseDto> getAllTags();

    TagResponseDto getTagById(Long id);

    TagResponseDto createTag(TagRequestDto requestDTO);

    TagResponseDto updateTag(Long id, TagRequestDto requestDTO);

    void deleteTag(Long id);
}
