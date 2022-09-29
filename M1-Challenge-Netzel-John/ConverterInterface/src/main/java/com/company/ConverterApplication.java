package com.company;

import java.util.Random;

public class ConverterApplication {
    private static final int NUM_MONTHS = 12;
    private static final int NUM_DAYS_IN_WEEK = 7;
    private static final Random RNG = new Random();

    public static void main(String[] args) {
        ConverterIf converterIf = new ConverterIf();
        ConverterSwitch converterSwitch = new ConverterSwitch();

        // Remember that nexInt(range) returns start at 0 so need to be offset by 1
        int testMonth1 = RNG.nextInt(NUM_MONTHS) + 1;
        int testMonth2 = RNG.nextInt(NUM_MONTHS) + 1;
        int testMonth3 = RNG.nextInt(NUM_MONTHS) + 1;

        int testDayOfWeek1 = RNG.nextInt(NUM_DAYS_IN_WEEK) + 1;
        int testDayOfWeek2 = RNG.nextInt(NUM_DAYS_IN_WEEK) + 1;
        int testDayOfWeek3 = RNG.nextInt(NUM_DAYS_IN_WEEK) + 1;

        System.out.println("Test month values are: " + testMonth1 + ", " + testMonth2 + ", " + testMonth3);
        System.out.println("Test day of week values are: " + testDayOfWeek1 + ", " + testDayOfWeek2 + ", " +
                testDayOfWeek3);
    }
}
