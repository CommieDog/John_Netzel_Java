package com.company.monthmathservice.controllers;

import com.company.monthmathservice.models.Month;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MonthController {
    @GetMapping(value = "/month/{monthNumber}")
    public Month getMonthByNumber(@PathVariable int monthNumber) {
        return new Month(1);
    }

    @GetMapping(value = "/randomMonth")
    public Month getRandomMonth() {
        return new Month(1);
    }
}
