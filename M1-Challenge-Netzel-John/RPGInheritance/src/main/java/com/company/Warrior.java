package com.company;

public class Warrior extends Character {
    private int shieldStrength;

    public Warrior(String name) {
        super(name, 75, 100, 100, 50, 100);
        this.shieldStrength = 100;
    }

    public int getShieldStrength() {
        return shieldStrength;
    }

    public void setShieldStrength(int shieldStrength) {
        this.shieldStrength = shieldStrength;
    }
}
