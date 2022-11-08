package com.company.musicstorecatalog.controller;

import com.company.musicstorecatalog.model.Artist;
import com.company.musicstorecatalog.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Artist createLabel(@RequestBody Artist label) {
        return artistRepo.save(label);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteLabel(@PathVariable Integer id) {
        artistRepo.deleteById(id);
    }

    @PutMapping(value = "/{id}")
    public void updateLabel(@PathVariable Integer id, @RequestBody Artist label) {
        artistRepo.save(label);
    }
}
