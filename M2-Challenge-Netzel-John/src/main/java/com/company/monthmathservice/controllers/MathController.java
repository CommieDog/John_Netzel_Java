package com.company.monthmathservice.controllers;

import com.company.monthmathservice.models.MathSolution;
import org.springframework.web.bind.annotation.*;

@RestController
public class MathController {
    @PostMapping(value = "/add")
    public MathSolution addNumbers(@RequestBody MathSolution body) {
        body.setOperation("add");
        body.calculateAnswer();
        return body;
    }

    @PostMapping(value = "/subtract")
    public MathSolution subtractNumbers(@RequestBody MathSolution body) {
        return body;
    }

    @PostMapping(value = "/multiply")
    public MathSolution multiplyNumbers(@RequestBody MathSolution body) {
        return body;
    }

    @PostMapping(value = "/divide")
    public MathSolution divideNumbers(@RequestBody MathSolution body) {
        return body;
    }
}
