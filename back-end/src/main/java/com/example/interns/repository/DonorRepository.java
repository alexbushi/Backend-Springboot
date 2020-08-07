package com.example.interns.repository;
import com.example.interns.model.Donor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DonorRepository extends MongoRepository<Donor, Long> {

}