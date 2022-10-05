package com.company.monthmathservice.controllers;

import com.company.monthmathservice.models.Month;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class MonthController {
    // Each instance of controller has own RNG
    private final Random RNG = new Random();

    @GetMapping(value = "/month/{monthNumber}")
    public Month getMonthByNumber(@PathVariable int monthNumber) {
        return new Month(monthNumber);
    }

    @GetMapping(value = "/randomMonth")
    public Month getRandomMonth() {
        int monthNumber = RNG.nextInt(12) + 1; // rng number range is 0..11
        return new Month(monthNumber);
    }
}
