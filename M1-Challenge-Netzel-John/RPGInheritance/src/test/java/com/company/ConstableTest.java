package com.company;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConstableTest {
    @Test
    public void shouldArrestCharacter() {
        // Arrange
        Constable constable = new Constable("Constable");
        Character target = new Warrior("Warrior");
        // Act
        constable.arrest(target);
        // Assert
        assertTrue(target.isArrested());
    }
}