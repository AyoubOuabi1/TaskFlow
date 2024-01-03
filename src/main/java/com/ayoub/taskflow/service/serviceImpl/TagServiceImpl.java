package com.ayoub.taskflow.service.serviceImpl;
import com.ayoub.taskflow.dto.TagDTO;
import com.ayoub.taskflow.entities.Tag;
import com.ayoub.taskflow.exception.NotFoundException;
import com.ayoub.taskflow.repository.TagRepository;
import com.ayoub.taskflow.service.TagService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final ModelMapper modelMapper;

     public TagServiceImpl(TagRepository tagRepository, ModelMapper modelMapper) {
        this.tagRepository = tagRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TagDTO> getAllTags() {
        List<Tag> tags = tagRepository.findAll();
        return tags.stream()
                .map(tag ->
                        modelMapper.map(tag,TagDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TagDTO getTagById(Long tagId) {
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new NotFoundException("Tag not found with id: " + tagId));
        return modelMapper.map(tag,TagDTO.class);
    }

    @Override
    public List<Tag> findAllTagsById(Set<Long> tagIds) {
        return tagRepository.findAllById(tagIds);
    }

    @Override
    public TagDTO createTag(TagDTO tagDTO) {
        Tag tag = modelMapper.map(tagDTO,Tag.class);
        Tag savedTag = tagRepository.save(tag);
        return modelMapper.map(savedTag,TagDTO.class);
    }

    @Override
    public TagDTO updateTag(Long tagId, TagDTO tagDTO) {

        return null;
    }

    @Override
    public void deleteTag(Long tagId) {
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new NotFoundException("Tag not found with id: " + tagId));
            tagRepository.deleteById(tagId);

    }
    @Override
    public boolean existsById(Long userId){
        boolean tagExist= tagRepository.existsById(userId);
        if(tagExist){
            return true;
        }
        return false;
    }


}
