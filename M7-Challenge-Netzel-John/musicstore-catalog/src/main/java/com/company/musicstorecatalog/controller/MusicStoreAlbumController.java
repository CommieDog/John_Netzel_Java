package com.company.musicstorecatalog.controller;

import com.company.musicstorecatalog.model.Album;
import com.company.musicstorecatalog.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Album createLabel(@RequestBody Album label) {
        return albumRepo.save(label);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteLabel(@PathVariable Integer id) {
        albumRepo.deleteById(id);
    }

    @PutMapping(value = "/{id}")
    public void updateLabel(@PathVariable Integer id, @RequestBody Album label) {
        albumRepo.save(label);
    }
}
