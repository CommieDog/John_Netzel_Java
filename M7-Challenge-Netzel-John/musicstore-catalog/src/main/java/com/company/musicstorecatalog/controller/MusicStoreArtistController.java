package com.company.musicstorecatalog.controller;

import com.company.musicstorecatalog.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/artist")
public class MusicStoreArtistController {
    @Autowired
    private ArtistRepository artistRepo;

    @GetMapping
    private String testController() {
        return "Success!";
    }
}
