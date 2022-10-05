package com.company.monthmathservice.models;

public class Month {
    private int number;
    private String name;

    public Month(int number) {
        this.number = number;
        this.name = "Month";
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
}
