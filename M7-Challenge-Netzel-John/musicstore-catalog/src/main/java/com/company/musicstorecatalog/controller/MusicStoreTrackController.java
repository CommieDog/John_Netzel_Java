package com.company.musicstorecatalog.controller;

import com.company.musicstorecatalog.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/track")
public class MusicStoreTrackController {
    @Autowired
    private TrackRepository trackRepo;

    @GetMapping
    private String testController() {
        return "Success!";
    }
}
