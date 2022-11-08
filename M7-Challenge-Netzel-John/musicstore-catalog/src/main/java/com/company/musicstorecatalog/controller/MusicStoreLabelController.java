package com.company.musicstorecatalog.controller;

import com.company.musicstorecatalog.model.Label;
import com.company.musicstorecatalog.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/label")
public class MusicStoreLabelController {
    @Autowired
    private LabelRepository labelRepo;

    @GetMapping
    public List<Label> getAllLabels() {
        return labelRepo.findAll();
    }

    @GetMapping(value = "/{id}")
    public Label getLabelById(@PathVariable Integer id) {
        return labelRepo.findById(id).get();
    }

    @PostMapping
    public Label createLabel(@RequestBody Label label) {
        System.out.println(label);
        return labelRepo.save(label);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteLabel(@PathVariable Integer id) {
        labelRepo.deleteById(id);
    }

    @PutMapping(value = "/{id}")
    public void updateLabel(@PathVariable Integer id, @RequestBody Label label) {
        labelRepo.save(label);
    }
}
