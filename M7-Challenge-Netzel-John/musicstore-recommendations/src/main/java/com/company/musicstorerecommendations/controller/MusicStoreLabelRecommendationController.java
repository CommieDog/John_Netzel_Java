package com.company.musicstorerecommendations.controller;

import com.company.musicstorerecommendations.model.LabelRecommendation;
import com.company.musicstorerecommendations.repository.LabelRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/label")
public class MusicStoreLabelRecommendationController {
    @Autowired
    private LabelRecommendationRepository labelRepo;

    @GetMapping
    public List<LabelRecommendation> getAllLabels() {
        return labelRepo.findAll();
    }

    @GetMapping(value = "/{id}")
    public LabelRecommendation getLabelById(@PathVariable Integer id) {
        return labelRepo.findById(id).get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LabelRecommendation createLabel(@RequestBody LabelRecommendation label) {
        return labelRepo.save(label);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLabel(@PathVariable Integer id) {
        if(!labelRepo.findById(id).isPresent()) // Check to make sure record with ID exists
            throw new NoSuchElementException("Attempting to delete record that does not exist:" + id);
        labelRepo.deleteById(id);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLabel(@PathVariable Integer id, @RequestBody LabelRecommendation label) {
        if(!labelRepo.findById(id).isPresent()) // Check to make sure record with ID exists
            throw new NoSuchElementException("Attempting to update record that does not exist:" + id);
        labelRepo.save(label);
    }
}
