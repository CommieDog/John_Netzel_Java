package com.company.monthmathservice.controllers;

import com.company.monthmathservice.models.MathSolution;
import org.springframework.web.bind.annotation.*;

@RestController
public class MathController {
    @PostMapping(value = "/add")
    public MathSolution addNumbers(@RequestBody double operand1, double operand2) {
        return new MathSolution(operand1, operand2, "add");
    }

    @PostMapping(value = "/subtract")
    public MathSolution subtractNumbers(@RequestBody double operand1, double operand2) {
        return new MathSolution(operand1, operand2, "subtract");
    }

    @PostMapping(value = "/multiply")
    public MathSolution multiplyNumbers(@RequestBody double operand1, double operand2) {
        return new MathSolution(operand1, operand2, "multiply");
    }

    @PostMapping(value = "/divide")
    public MathSolution divideNumbers(@RequestBody double operand1, double operand2) {
        return new MathSolution(operand1, operand2, "divide");
    }
}
