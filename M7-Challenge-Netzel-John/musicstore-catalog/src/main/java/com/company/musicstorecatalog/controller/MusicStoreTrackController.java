package com.company.musicstorecatalog.controller;

import com.company.musicstorecatalog.model.Track;
import com.company.musicstorecatalog.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Track createLabel(@RequestBody Track label) {
        return trackRepo.save(label);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteLabel(@PathVariable Integer id) {
        trackRepo.deleteById(id);
    }

    @PutMapping(value = "/{id}")
    public void updateLabel(@PathVariable Integer id, @RequestBody Track label) {
        trackRepo.save(label);
    }
}
