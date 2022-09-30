package com.company.vendor;

public class IceCream {
    private static final double MELTING_POINT = 32.0; // in Fahrenheit

    private double servingSize;
    private double cost;
    private String flavor;
    private double temperature;
    private boolean sold;

    public IceCream()
    {
        this.servingSize = 2.0; // 2 fl oz, a small scoop
        this.cost = 0.0; // free ice cream!
        this.flavor = null;
        this.temperature = 0; // Intended to be 0 degrees Fahrenheit, well below the melting point of water
        this.sold = false;
    }

    public double getServingSize() {
        return servingSize;
    }

    public void setServingSize(double servingSize) {
        if(servingSize > 0.0) {
            this.servingSize = servingSize;
        }
        else {
            System.out.println("Can't set negative or 0 serving size.");
        }
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        if(cost >= 0.0) {
            this.cost = cost;
        }
        else {
            System.out.println("We're not paying people to buy our ice cream!");
        }
    }

    public String getFlavor() {
        if(flavor == null) {
            return "unknown";
        }
        if(flavor.equals("")) {
            return "unknown";
        }
        return flavor;
    }

    public void setFlavor(String flavor) {
        if(flavor == null) {
            System.out.println("Ice cream must have a flavor.");
        }
        else if(flavor.equals("")) {
            System.out.println("Ice cream must have a flavor.");
        }
        else {
            this.flavor = flavor;
        }
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

    public String sell() {
        if(getFlavor().equals("unknown")) {
            return "Can't sell ice cream without knowing its flavor!";
        }
        return null;
    }

    public boolean isMelted() {
        return false;
    }
}
