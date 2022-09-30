package com.company;

import java.util.Random;
import java.util.Scanner;

public class ConverterApplication {
    private static final int NUM_MONTHS = 12;
    private static final int NUM_DAYS_IN_WEEK = 7;
    private static final Random RNG = new Random();

    public static void main(String[] args) {
        Converter converterIf = new ConverterIf();
        Converter converterSwitch = new ConverterSwitch();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a number to convert to a month.");
        int input = Integer.parseInt(scanner.nextLine());
        String converterIfResponse = converterIf.convertMonth(input);
        String converterSwitchResponse = converterSwitch.convertMonth(input);
        System.out.println("ConverterIf response: " + converterIfResponse);
        System.out.println("ConverterSwitch response: " + converterSwitchResponse);

        System.out.println("Enter a number to convert to a day.");
        input = Integer.parseInt(scanner.nextLine());
        converterIfResponse = converterIf.convertDay(input);
        converterSwitchResponse = converterSwitch.convertDay(input);
        System.out.println("ConverterIf response: " + converterIfResponse);
        System.out.println("ConverterSwitch response: " + converterSwitchResponse);
    }
}
