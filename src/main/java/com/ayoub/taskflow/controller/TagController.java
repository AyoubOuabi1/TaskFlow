package com.ayoub.taskflow.controller;
import com.ayoub.taskflow.dto.TagDTO;
import com.ayoub.taskflow.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping
    public ResponseEntity<List<TagDTO>> getAllTags() {
        List<TagDTO> tags = tagService.getAllTags();
        return ResponseEntity.ok(tags);
    }

    @GetMapping("/{tagId}")
    public ResponseEntity<TagDTO> getTagById(@PathVariable Long tagId) {
        TagDTO tag = tagService.getTagById(tagId);
        return ResponseEntity.ok(tag);
    }

    @PostMapping
    public ResponseEntity<TagDTO> createTag(@RequestBody TagDTO tagDTO) {
        TagDTO createdTag = tagService.createTag(tagDTO);
        return ResponseEntity.ok(createdTag);
    }

    @PutMapping("/{tagId}")
    public ResponseEntity<TagDTO> updateTag(@PathVariable Long tagId, @RequestBody TagDTO tagDTO) {
      return null;
    }

    @DeleteMapping("/{tagId}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long tagId) {
        tagService.deleteTag(tagId);
        return ResponseEntity.noContent().build();
    }
}
