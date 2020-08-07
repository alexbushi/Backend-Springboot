package com.example.interns.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.example.interns.error_handling.ResourceNotFoundException;
import com.example.interns.model.Donor;
import com.example.interns.repository.SequenceGeneratorService;
import com.example.interns.repository.DonorRepository;

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
public class DonorController {

    @Autowired
    private DonorRepository repository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @PostMapping("/create_donor")
    public Map<String, Object> createDonor(@Valid @RequestBody Donor donor) {
        donor.setId(sequenceGeneratorService.generateSequence(Donor.SEQUENCE_NAME));

        repository.save(donor);
        Map<String, Object> response = new HashMap<>();
        response.put("msg", "Created donor");
        response.put("createdStory", donor);
        return response;
    }

    @PutMapping("/donor/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Donor> updateDonor(@PathVariable(value = "id") long id,
            @Valid @RequestBody Donor donorDetails) throws ResourceNotFoundException {
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Donor not found for id: " + id));

        donorDetails.setId(id);

        final Donor updatedStory = repository.save(donorDetails);
        return ResponseEntity.ok(updatedStory);
    }

    @DeleteMapping("/donor/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Object> deleteDonorById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Donor donor = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Donor not found for id: " + id));

        repository.delete(donor);
        Map<String, Object> response = new HashMap<>();
        response.put("deletedDonorId", id);
        return response;
    }

    @GetMapping("/donor")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Donor> getAllDonors() {
        return repository.findAll();
    }

    @GetMapping("/donor/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Donor> getDonorById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Donor donor = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Donor not found for id:" + id));
        return ResponseEntity.ok().body(donor);
    }

}