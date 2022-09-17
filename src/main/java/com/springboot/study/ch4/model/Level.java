package com.springboot.study.ch4.model;

import lombok.Getter;

@Getter
public enum Level {
  GOLD(3, null),
  SILVER(2, GOLD),
  BRONZE(1, Level.SILVER);

  private final int value;
  private final Level nextLevel;


  Level(int value, Level nextLevel) {
    this.value = value;
    this.nextLevel = nextLevel;
  }

  public boolean hasNextLevel() {
    return this.nextLevel != null;
  }

  public static Level valueOf(int value) {
    for (Level l : Level.values()) {
      if (l.value == value) {
        return l;
      }
    }
    throw new AssertionError("Unknown value: " + value);
  }
}
