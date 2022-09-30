package com.company;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {
    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void shouldAddIntegers() {
        // Arrange
        int expectedResult1 = 2;
        int expectedResult2 = -333;
        int expectedResult3 = 50;
        // Act
        int actualResult1 = calculator.add(1, 1);
        int actualResult2 = calculator.add(123, -456);
        int actualResult3 = calculator.add(50, 0);
        // Assert
        assertEquals(expectedResult1, actualResult1);
        assertEquals(expectedResult2, actualResult2);
        assertEquals(expectedResult3, actualResult3);
    }

    @Test
    public void shouldSubtractIntegers() {
        // Arrange
        int expectedResult1 = 0;
        int expectedResult2 = 579;
        int expectedResult3 = 50;
        // Act
        int actualResult1 = calculator.subtract(1, 1);
        int actualResult2 = calculator.subtract(123, -456);
        int actualResult3 = calculator.subtract(50, 0);
        // Assert
        assertEquals(expectedResult1, actualResult1);
        assertEquals(expectedResult2, actualResult2);
        assertEquals(expectedResult3, actualResult3);
    }

    @Test
    public void shouldMultiplyIntegers() {
        // Arrange
        int expectedResult1 = 1;
        int expectedResult2 = -56088;
        int expectedResult3 = 0;
        // Act
        int actualResult1 = calculator.multiply(1, 1);
        int actualResult2 = calculator.multiply(123, -456);
        int actualResult3 = calculator.multiply(50, 0);
        // Assert
        assertEquals(expectedResult1, actualResult1);
        assertEquals(expectedResult2, actualResult2);
        assertEquals(expectedResult3, actualResult3);
    }

    @Test
    public void shouldDivideIntegers() {
        // Arrange
        int expectedResult1 = 17;
        int expectedResult2 = -3;
        int expectedResult3 = 0;
        // Act
        int actualResult1 = calculator.divide(17, 1);
        int actualResult2 = calculator.divide(456, -123);
        int actualResult3 = calculator.divide(0, 50);
        // Assert
        assertEquals(expectedResult1, actualResult1);
        assertEquals(expectedResult2, actualResult2);
        assertEquals(expectedResult3, actualResult3);
    }

    @Test
    public void shouldAddDoubles() {
    }

    @Test
    public void shouldSubtractDoubles() {
    }

    @Test
    public void shouldMultiplyDoubles() {
    }

    @Test
    public void shouldDivideDoubles() {
    }
}