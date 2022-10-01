package com.company.vendor;

import java.util.Date;

public class IceCream {
    private static final double MELTING_POINT = 32.0; // in Fahrenheit

    private double servingSize;
    private double cost;
    private String flavor;
    private double temperature;
    private boolean sold;
    private Date openedTime; // The time the batch that this ice cream is from was opened

    public IceCream(double servingSize, double cost, String flavor)
    {
        this.servingSize = servingSize;
        this.cost = cost;
        this.flavor = flavor;
        this.temperature = 0.0; // Intended to be 0 degrees Fahrenheit, well below the melting point of water
        this.sold = false;
    }

    public double getServingSize() {
        return servingSize;
    }

    public double getCost() {
        return cost;
    }

    public String getFlavor() {
        return flavor;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public boolean isSold() {
        return sold;
    }

    public Date getOpenedTime() {
        return openedTime;
    }

    public void open() {
        openedTime = new Date(); // Date constructor with no params defaults to current time
    }

    public void sell() {
        sold = true;
        // Probably want code to credit vendor for the cost
    }

    public boolean isOpened() {
        return getOpenedTime() == null; // null means "hasn't been opened yet"
    }

    public boolean isMelted() {
        return getTemperature() >= 32.0; // 32 degrees Fahrenheit isn't fully melted, but who wants to eat half-melted mush?
    }

    // Is the ice cream spoiled and eligible to be written off as a cost of business?
    public boolean isSpoiledForWriteOff() {
        if(isSold()) {
            return false; // Ice cream that has already been sold is the customer's problem
        }
        return isMelted() && !isOpened(); // Ice cream must be melted and unopened for write off
    }
}
