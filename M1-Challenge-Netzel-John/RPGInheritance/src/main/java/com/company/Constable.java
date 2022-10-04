package com.company;

public class Constable extends Character {
    private String jurisdiction;

    public Constable(String name) {
        super(name, 60, 100, 60, 20, 5);
    }

    public void arrest(Character target) {
        // May want to do a check of the constable's jurisdiction against the current location
        target.setArrested(true);
    }
}
