package com.example.interns.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.example.interns.error_handling.ResourceNotFoundException;
import com.example.interns.model.Story;
import com.example.interns.repository.SequenceGeneratorService;
import com.example.interns.repository.StoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/v1")
public class StoryController {

    @Autowired
    private StoryRepository repository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @PostMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Object> createStory(@Valid @RequestBody Story story) {
        // error handling for db connection
        story.setId(sequenceGeneratorService.generateSequence(Story.SEQUENCE_NAME));
        repository.save(story);
        Map<String, Object> response = new HashMap<>();
        response.put("msg", "Created story");
        response.put("createdStory", story);
        return response;
    }

    @PutMapping("/admin/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    // HTTP status
    // Still handle exceptions gracefully, give better message, one part fails only
    public ResponseEntity<Story> updateStory(@PathVariable(value = "id") long id,
            @Valid @RequestBody Story storyDetails) throws ResourceNotFoundException {
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Story not found for id: " + id));

        storyDetails.setId(id);

        final Story updatedStory = repository.save(storyDetails);
        return ResponseEntity.ok(updatedStory);
    }

    @DeleteMapping("/admin/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Object> deleteStoryById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Story story = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Story not found for id: " + id));

        repository.delete(story);
        Map<String, Object> response = new HashMap<>();
        response.put("deletedStoryId", id);
        return response;
    }

    @GetMapping("/story_callout")
    public List<Story> getAllStories() {
        return repository.findAll();
    }

    @GetMapping("/story_callout/{id}")
    public ResponseEntity<Story> getStoryById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Story story = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Story not found for id:" + id));
        return ResponseEntity.ok().body(story);
    }

}