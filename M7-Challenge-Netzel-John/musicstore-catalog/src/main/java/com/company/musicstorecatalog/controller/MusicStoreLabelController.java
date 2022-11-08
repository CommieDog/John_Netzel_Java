package com.company.musicstorecatalog.controller;

import com.company.musicstorecatalog.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/label")
public class MusicStoreLabelController {
    @Autowired
    private LabelRepository labelRepo;

    @GetMapping
    private String testController() {
        return "Success!";
    }
}
