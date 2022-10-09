package com.company.monthmathservice.models;

public class MathSolution {
    private double operand1;
    private double operand2;
    private String operation;
    private double answer;

    public MathSolution() {
        this.operation = "";
    }

    public MathSolution(double operand1, double operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operation = "";
    }

    public double getOperand1() {
        return operand1;
    }

    public void setOperand1(double operand1) {
        this.operand1 = operand1;
    }

    public double getOperand2() {
        return operand2;
    }

    public void setOperand2(double operand2) {
        this.operand2 = operand2;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public double getAnswer() {
        return answer;
    }

    public void setAnswer(double answer) {
        this.answer = answer;
    }

    public void calculateAnswer() {
        answer = 0.0;
    }
}
