package com.company.factory;

import java.util.Date;

public class IceCream {
    private static final double MIN_TEMPERATURE_FOR_QA = 5; // in Fahrenheit
    private String flavor;
    private String[] ingredients;
    private int mixingTime; // in ms
    private Date mixingStartTime;
    private Date mixingFinishTime;
    private double temperature;

    public IceCream(String flavor, String[] ingredients, int mixingTime) {
        this.flavor = flavor;
        this.ingredients = ingredients;
        this.mixingTime = mixingTime;
        this.temperature = 72; // Assume the worst (room temperature) by default
    }

    public String getFlavor() {
        return flavor;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public int getMixingTime() {
        return mixingTime;
    }

    public Date getMixingStartTime() {
        return mixingStartTime;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public boolean isFinishedMixing() {
        if(getMixingStartTime() == null) {
            return false; // We haven't even started mixing!
        }
        return mixingFinishTime.after(new Date());
    }

    public void startMixing() {
        mixingStartTime = new Date();  // Date constructor with no params defaults to current time
        mixingFinishTime = new Date(getMixingStartTime().getTime() + getMixingTime()); // Add mixing time to the start time
    }

    public void stopMixing() {
        if(!isFinishedMixing()) {
            // reset the mixing start and finish times, so we have to start over
            mixingStartTime = null;
            mixingFinishTime = null;
        }
    }

    public boolean checkForQA() {
        return isFinishedMixing() && (getTemperature() < MIN_TEMPERATURE_FOR_QA); // Must be finished mixing and cold enough to pass QA
    }
}
