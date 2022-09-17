package com.springboot.study.ch4.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LevelTest {

  @Test
  void get_next_level() {
    assertEquals(Level.SILVER, Level.BRONZE.getNextLevel());
    assertEquals(Level.GOLD, Level.SILVER.getNextLevel());
    assertEquals(null, Level.GOLD.getNextLevel());
  }

  @Test
  void has_next_level() {
    assertTrue(Level.BRONZE.hasNextLevel());
    assertTrue(Level.SILVER.hasNextLevel());
    assertFalse(Level.GOLD.hasNextLevel());
  }
}