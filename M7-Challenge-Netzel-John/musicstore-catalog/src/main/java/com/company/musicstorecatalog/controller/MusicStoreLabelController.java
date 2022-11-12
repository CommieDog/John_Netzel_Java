package com.company.musicstorecatalog.controller;

import com.company.musicstorecatalog.model.Label;
import com.company.musicstorecatalog.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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
    @ResponseStatus(HttpStatus.CREATED)
    public Label createLabel(@RequestBody Label label) {
        return labelRepo.save(label);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLabel(@PathVariable Integer id) {
        if(!labelRepo.findById(id).isPresent()) // Check to make sure record with ID exists
            throw new NoSuchElementException("Attempting to delete record that does not exist:" + id);
        labelRepo.deleteById(id);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLabel(@PathVariable Integer id, @RequestBody Label label) {
        if(!labelRepo.findById(id).isPresent()) // Check to make sure record with ID exists
            throw new NoSuchElementException("Attempting to update record that does not exist:" + id);
        labelRepo.save(label);
    }
}
