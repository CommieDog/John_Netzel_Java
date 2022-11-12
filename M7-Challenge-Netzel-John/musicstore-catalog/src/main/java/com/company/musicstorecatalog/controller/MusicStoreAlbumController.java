package com.company.musicstorecatalog.controller;

import com.company.musicstorecatalog.model.Album;
import com.company.musicstorecatalog.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/album")
public class MusicStoreAlbumController {
    @Autowired
    private AlbumRepository albumRepo;

    @GetMapping
    public List<Album> getAllLabels() {
        return albumRepo.findAll();
    }

    @GetMapping(value = "/{id}")
    public Album getLabelById(@PathVariable Integer id) {
        return albumRepo.findById(id).get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Album createLabel(@RequestBody Album label) {
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
    public void updateLabel(@PathVariable Integer id, @RequestBody Album label) {
        if(!albumRepo.findById(id).isPresent()) // Check to make sure record with ID exists
            throw new NoSuchElementException("Attempting to update record that does not exist:" + id);
        albumRepo.save(label);
    }
}
