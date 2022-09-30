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
    public void shouldNotConvertNumberToMonthIfOutOfBounds() {
        // Arrange
        String expectedValue1 = "Error: convertMonth input 0 is out of range";
        String expectedValue2 = "Error: convertMonth input 13 is out of range";

        // Act
        String actualValue1 = converter.convertMonth(0);
        String actualValue2 = converter.convertMonth(13);

        // Assert
        assertEquals(expectedValue1, actualValue1);
        assertEquals(expectedValue2, actualValue2);
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

    @Test
    public void shouldNotConvertNumberToDayIfOutOfBounds() {
        // Arrange
        String expectedValue1 = "Error: convertDay input 0 is out of range";
        String expectedValue2 = "Error: convertDay input 8 is out of range";

        // Act
        String actualValue1 = converter.convertDay(0);
        String actualValue2 = converter.convertDay(8);

        // Assert
        assertEquals(expectedValue1, actualValue1);
        assertEquals(expectedValue2, actualValue2);
    }
}