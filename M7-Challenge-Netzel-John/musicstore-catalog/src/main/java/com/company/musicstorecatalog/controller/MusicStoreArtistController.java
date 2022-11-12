package com.company.musicstorecatalog.controller;

import com.company.musicstorecatalog.model.Artist;
import com.company.musicstorecatalog.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/artist")
public class MusicStoreArtistController {
    @Autowired
    private ArtistRepository artistRepo;

    @GetMapping
    public List<Artist> getAllLabels() {
        return artistRepo.findAll();
    }

    @GetMapping(value = "/{id}")
    public Artist getLabelById(@PathVariable Integer id) {
        return artistRepo.findById(id).get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Artist createLabel(@RequestBody Artist label) {
        return artistRepo.save(label);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLabel(@PathVariable Integer id) {
        if(!artistRepo.findById(id).isPresent()) // Check to make sure record with ID exists
            throw new NoSuchElementException("Attempting to delete record that does not exist:" + id);
        artistRepo.deleteById(id);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLabel(@PathVariable Integer id, @RequestBody Artist label) {
        if(!artistRepo.findById(id).isPresent()) // Check to make sure record with ID exists
            throw new NoSuchElementException("Attempting to update record that does not exist:" + id);
        artistRepo.save(label);
    }
}
