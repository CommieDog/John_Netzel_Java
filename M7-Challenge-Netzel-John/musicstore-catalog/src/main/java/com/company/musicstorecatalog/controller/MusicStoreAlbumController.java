package com.company.musicstorecatalog.controller;

import com.company.musicstorecatalog.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/album")
public class MusicStoreAlbumController {
    @Autowired
    private AlbumRepository albumRepo;

    @GetMapping
    private String testController() {
        return "Success!";
    }
}
