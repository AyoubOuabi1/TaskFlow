package com.ayoub.taskflow.service.serviceImpl;
import com.ayoub.taskflow.dto.TagDTO;
import com.ayoub.taskflow.exception.TagNotFoundException;
import com.ayoub.taskflow.mapper.TagMapper;
import com.ayoub.taskflow.entities.Tag;
import com.ayoub.taskflow.repository.TagRepository;
import com.ayoub.taskflow.service.TagService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

     public TagServiceImpl(TagRepository tagRepository, TagMapper tagMapper) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }

    @Override
    public List<TagDTO> getAllTags() {
        List<Tag> tags = tagRepository.findAll();
        return tags.stream().map(tagMapper::toTagDTO).collect(Collectors.toList());
    }

    @Override
    public TagDTO getTagById(Long tagId) {
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new TagNotFoundException("Tag not found with id: " + tagId));
        return tagMapper.toTagDTO(tag);
    }

    @Override
    public TagDTO createTag(TagDTO tagDTO) {
        Tag tag = tagMapper.toTag(tagDTO);
        Tag savedTag = tagRepository.save(tag);
        return tagMapper.toTagDTO(savedTag);
    }

    @Override
    public TagDTO updateTag(Long tagId, TagDTO tagDTO) {

        return null;
    }

    @Override
    public void deleteTag(Long tagId) {
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new TagNotFoundException("Tag not found with id: " + tagId));
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
    @Override
    public Set<Tag> getTagsByIds(Set<Long> tagIds) {
        return tagRepository.findAllById(tagIds)
                .stream()
                .collect(Collectors.toSet());
    }

}
