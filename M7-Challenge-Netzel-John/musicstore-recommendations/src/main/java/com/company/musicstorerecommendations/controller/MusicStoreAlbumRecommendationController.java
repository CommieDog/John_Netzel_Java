package com.company.musicstorerecommendations.controller;

import com.company.musicstorerecommendations.model.AlbumRecommendation;
import com.company.musicstorerecommendations.repository.AlbumRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/album")
public class MusicStoreAlbumRecommendationController {
    @Autowired
    private AlbumRecommendationRepository albumRepo;

    @GetMapping
    public List<AlbumRecommendation> getAllLabels() {
        return albumRepo.findAll();
    }

    @GetMapping(value = "/{id}")
    public AlbumRecommendation getLabelById(@PathVariable Integer id) {
        return albumRepo.findById(id).get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumRecommendation createLabel(@RequestBody AlbumRecommendation label) {
        return albumRepo.save(label);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLabel(@PathVariable Integer id) {
        if(!albumRepo.findById(id).isPresent()) // Check to make sure record with ID exists
            throw new NoSuchElementException("Attempting to delete record that does not exist:" + id);
        albumRepo.deleteById(id);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLabel(@PathVariable Integer id, @RequestBody AlbumRecommendation label) {
        if(!albumRepo.findById(id).isPresent()) // Check to make sure record with ID exists
            throw new NoSuchElementException("Attempting to update record that does not exist:" + id);
        albumRepo.save(label);
    }
}
