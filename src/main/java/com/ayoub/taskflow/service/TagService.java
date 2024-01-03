package com.ayoub.taskflow.service;


import com.ayoub.taskflow.dto.TagDTO;
import com.ayoub.taskflow.entities.Tag;

import java.util.List;
import java.util.Set;

public interface TagService {
    List<TagDTO> getAllTags();

    TagDTO getTagById(Long tagId);
    List<Tag> findAllTagsById(Set<Long> tagIds);
    TagDTO createTag(TagDTO tagDTO);

    TagDTO updateTag(Long tagId, TagDTO tagDTO);

    void deleteTag(Long tagId);
    boolean existsById(Long userId);
}
