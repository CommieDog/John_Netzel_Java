package com.company;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {
    // The amount of error tolerance for floating-point arithmetic
    private static final double FP_DELTA = 0.001;
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
        // Arrange
        double expectedResult1 = 2.0;
        double expectedResult2 = -333.38;
        double expectedResult3 = 50.1;
        // Act
        double actualResult1 = calculator.add(1.0, 1.0);
        double actualResult2 = calculator.add(123.4, -456.78);
        double actualResult3 = calculator.add(50.1, 0.0);
        // Assert
        assertEquals(expectedResult1, actualResult1, FP_DELTA);
        assertEquals(expectedResult2, actualResult2, FP_DELTA);
        assertEquals(expectedResult3, actualResult3, FP_DELTA);
    }

    @Test
    public void shouldSubtractDoubles() {
        // Arrange
        double expectedResult1 = 0.0;
        double expectedResult2 = 580.18;
        double expectedResult3 = 50.1;
        // Act
        double actualResult1 = calculator.subtract(1.0, 1.0);
        double actualResult2 = calculator.subtract(123.4, -456.78);
        double actualResult3 = calculator.subtract(50.1, 0.0);
        // Assert
        assertEquals(expectedResult1, actualResult1, FP_DELTA);
        assertEquals(expectedResult2, actualResult2, FP_DELTA);
        assertEquals(expectedResult3, actualResult3, FP_DELTA);
    }

    @Test
    public void shouldMultiplyDoubles() {
        // Arrange
        double expectedResult1 = 1.0;
        double expectedResult2 = -56366.652;
        double expectedResult3 = 0.0;
        // Act
        double actualResult1 = calculator.multiply(1.0, 1.0);
        double actualResult2 = calculator.multiply(123.4, -456.78);
        double actualResult3 = calculator.multiply(50.1, 0.0);
        // Assert
        assertEquals(expectedResult1, actualResult1, FP_DELTA);
        assertEquals(expectedResult2, actualResult2, FP_DELTA);
        assertEquals(expectedResult3, actualResult3, FP_DELTA);
    }

    @Test
    public void shouldDivideDoubles() {
        // Arrange
        double expectedResult1 = 1.0;
        double expectedResult2 = -0.270151;
        double expectedResult3 = 0.0;
        // Act
        double actualResult1 = calculator.divide(1.0, 1.0);
        double actualResult2 = calculator.divide(123.4, -456.78);
        double actualResult3 = calculator.divide(0.0, 50.1);
        // Assert
        assertEquals(expectedResult1, actualResult1, FP_DELTA);
        assertEquals(expectedResult2, actualResult2, FP_DELTA);
        assertEquals(expectedResult3, actualResult3, FP_DELTA);
    }
}