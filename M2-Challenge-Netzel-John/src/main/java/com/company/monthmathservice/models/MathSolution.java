package com.company.monthmathservice.models;

public class MathSolution {
    private double operand1;
    private double operand2;
    private String operation;
    private double answer;

    public MathSolution() {
    }

    public MathSolution(double operand1, double operand2, String operation) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operation = operation;
        this.answer = calculateAnswer();
    }

    public double getOperand1() {
        return operand1;
    }

    public double getOperand2() {
        return operand2;
    }

    public String getOperation() {
        return operation;
    }

    public double getAnswer() {
        return answer;
    }

    private double calculateAnswer() {
        return 0.0;
    }
}
