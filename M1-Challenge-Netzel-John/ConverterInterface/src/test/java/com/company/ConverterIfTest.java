package com.company;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConverterIfTest {
    private Converter converter;

    @Before
    public void setUp() {
        converter = new ConverterIf();
    }

    @Test
    public void shouldConvertNumberToMonth() {
        // Arrange
        String expectedValue1 = "March";
        String expectedValue2 = "April";
        String expectedValue3 = "December";

        // Act
        String actualValue1 = converter.convertMonth(3);
        String actualValue2 = converter.convertMonth(4);
        String actualValue3 = converter.convertMonth(12);

        // Assert
        assertEquals(expectedValue1, actualValue1);
        assertEquals(expectedValue2, actualValue2);
        assertEquals(expectedValue3, actualValue3);
    }

    @Test
    public void shouldConvertNumberToDay() {
        // Arrange
        String expectedValue1 = "Sunday";
        String expectedValue2 = "Wednesday";
        String expectedValue3 = "Saturday";

        // Act
        String actualValue1 = converter.convertDay(1);
        String actualValue2 = converter.convertDay(4);
        String actualValue3 = converter.convertDay(7);

        // Assert
        assertEquals(expectedValue1, actualValue1);
        assertEquals(expectedValue2, actualValue2);
        assertEquals(expectedValue3, actualValue3);
    }
}