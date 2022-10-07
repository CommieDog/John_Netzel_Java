package com.company.monthmathservice.models;

import java.util.Objects;

public class Month {
    public static final String[] MONTH_NAMES = {"January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December"};

    private int number;
    private String name;

    // Exception must be thrown up to MonthController to be properly handled by Spring
    public Month(int number) throws ArrayIndexOutOfBoundsException {
        this.number = number;
        this.name = MONTH_NAMES[number - 1]; // Remember that array indexes start at 0;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Month month = (Month) o;
        return number == month.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
