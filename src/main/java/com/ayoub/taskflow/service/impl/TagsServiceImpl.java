package com.ayoub.taskflow.service.impl;
import com.ayoub.taskflow.dto.requests.TagRequestDto;
import com.ayoub.taskflow.dto.responses.TagResponseDto;
import com.ayoub.taskflow.entities.Tag;
import com.ayoub.taskflow.repository.TagsRepository;
import com.ayoub.taskflow.service.TagsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagsServiceImpl implements TagsService {

    private   TagsRepository tagsRepository;
    private   ModelMapper modelMapper;

    public TagsServiceImpl(TagsRepository tagsRepository, ModelMapper modelMapper) {
        this.tagsRepository = tagsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TagResponseDto> getAllTags() {
        List<TagResponseDto> result = new ArrayList<>();
        tagsRepository.findAll().stream()
                .forEach( t->{
                    result.add(modelMapper.map(t,TagResponseDto.class));

                });
        return result;
    }

    @Override
    public TagResponseDto getTagById(Long id) {
        Tag tag = tagsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
        return modelMapper.map(tag,TagResponseDto.class);
    }

    @Override
    public TagResponseDto createTag(TagRequestDto requestDTO) {
        Tag tagToSave = modelMapper.map(requestDTO, Tag.class);
        Tag savedTag = tagsRepository.save(tagToSave);
        return modelMapper.map(savedTag,TagResponseDto.class);
    }

    @Override
    public TagResponseDto updateTag(Long id, TagRequestDto requestDTO) {
        Tag existingTag = tagsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
        existingTag.setName(requestDTO.getName());
        Tag updatedTag = tagsRepository.save(existingTag);
        return modelMapper.map(updatedTag,TagResponseDto.class);
    }

    @Override
    public void deleteTag(Long id) {
        tagsRepository.deleteById(id);
    }
}
