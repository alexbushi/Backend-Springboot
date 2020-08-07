package com.example.interns.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.interns.model.Story;

public interface StoryRepository extends MongoRepository<Story, Long> {

}