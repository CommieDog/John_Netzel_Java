package com.company;

import org.junit.Test;

import static org.junit.Assert.*;

public class CharacterTest {

    @Test
    public void shouldAttackingCharacterCauseDamageToTarget() {
        // Arrange
        Character attacker = new Warrior("Attacker");
        Character target = new Farmer("Target");
        int expectedHealth = 82;
        //Act
        attacker.attackCharacter(target);
        int actualHealth = target.getHealth();
        //Assert
        assertEquals(expectedHealth, actualHealth);
    }
}