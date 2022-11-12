package com.company.musicstorecatalog.controller;

import com.company.musicstorecatalog.model.Track;
import com.company.musicstorecatalog.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/track")
public class MusicStoreTrackController {
    @Autowired
    private TrackRepository trackRepo;

    @GetMapping
    public List<Track> getAllLabels() {
        return trackRepo.findAll();
    }

    @GetMapping(value = "/{id}")
    public Track getLabelById(@PathVariable Integer id) {
        return trackRepo.findById(id).get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Track createLabel(@RequestBody Track label) {
        return trackRepo.save(label);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLabel(@PathVariable Integer id) {
        if(!trackRepo.findById(id).isPresent()) // Check to make sure record with ID exists
            throw new NoSuchElementException("Attempting to delete record that does not exist:" + id);
        trackRepo.deleteById(id);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLabel(@PathVariable Integer id, @RequestBody Track label) {
        if(!trackRepo.findById(id).isPresent()) // Check to make sure record with ID exists
            throw new NoSuchElementException("Attempting to update record that does not exist:" + id);
        trackRepo.save(label);
    }
}
