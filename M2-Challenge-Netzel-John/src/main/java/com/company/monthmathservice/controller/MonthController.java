package com.company.monthmathservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MonthController {
    @GetMapping(value = "/month/{monthNumber}")
    public String getMonthByNumber(@PathVariable int monthNumber) {
        return "Month by number";
    }

    @GetMapping(value = "/randomMonth")
    public String getRandomMonth() {
        return "Random month here";
    }
}
