package com.company.monthmathservice.models;

import com.company.monthmathservice.controllers.UndefinedOperandException;

public class MathSolution {
    public static final String ADD_OPERATION = "add";
    public static final String SUBTRACT_OPERATION = "subtract";
    public static final String MULTIPLY_OPERATION = "multiply";
    public static final String DIVIDE_OPERATION = "divide";

    // Used Integer wrapper class to allow for null values
    private Integer operand1;
    private Integer operand2;
    private String operation;
    private Integer answer;

    public MathSolution() {
        this.operation = "";
    }

    public MathSolution(Integer operand1, Integer operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operation = "";
    }

    public Integer getOperand1() {
        return operand1;
    }

    public void setOperand1(Integer operand1) {
        this.operand1 = operand1;
    }

    public Integer getOperand2() {
        return operand2;
    }

    public void setOperand2(Integer operand2) {
        this.operand2 = operand2;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }

    public void calculateAnswer() {
        if(operand1 == null || operand2 == null) {
            throw new UndefinedOperandException();
        }
        switch (operation) {
            case ADD_OPERATION:
                answer = operand1 + operand2;
                break;
            case SUBTRACT_OPERATION:
                answer = operand1 - operand2;
                break;
            case MULTIPLY_OPERATION:
                answer = operand1 * operand2;
                break;
            case DIVIDE_OPERATION:
                answer = operand1 / operand2;
                break;
            default:
                // How did we get here?
                throw new RuntimeException("Operation not defined: " + operation);
        }
    }
}
