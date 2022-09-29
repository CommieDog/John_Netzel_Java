package com.company;

public class ConverterIf implements Converter {
    @Override
    public String convertMonth(int monthNumber) {
        String conversion;
        if(monthNumber == 1) {
            conversion = "January";
        }
        else if(monthNumber == 2) {
            conversion = "February";
        }
        else if(monthNumber == 3) {
            conversion = "March";
        }
        else if(monthNumber == 4) {
            conversion = "April";
        }
        else if(monthNumber == 5) {
            conversion = "May";
        }
        else if(monthNumber == 6) {
            conversion = "June";
        }
        else if(monthNumber == 7) {
            conversion = "July";
        }
        else if(monthNumber == 8) {
            conversion = "August";
        }
        else if(monthNumber == 9) {
            conversion = "September";
        }
        else if(monthNumber == 10) {
            conversion = "October";
        }
        else if(monthNumber == 11) {
            conversion = "November";
        }
        else if(monthNumber == 12) {
            conversion = "December";
        }
        else {
            conversion = "Error: convertMonth input " + monthNumber + " is out of range";
        }
        return conversion;
    }

    @Override
    public String convertDay(int dayNumber) {
        String conversion;
        if(dayNumber == 1) {
            conversion = "Sunday";
        }
        else if(dayNumber == 2) {
            conversion =  "Monday";
        }
        else if(dayNumber == 3) {
            conversion =  "Tuesday";
        }
        else if(dayNumber == 4) {
            conversion =  "Wednesday";
        }
        else if(dayNumber == 5) {
            conversion =  "Thursday";
        }
        else if(dayNumber == 6) {
            conversion =  "Friday";
        }
        else if(dayNumber == 7) {
            conversion =  "Saturday";
        }
        else {
            conversion = "Error: convertDay input " + dayNumber + " is out of range";
        }
        return conversion;
    }
}
