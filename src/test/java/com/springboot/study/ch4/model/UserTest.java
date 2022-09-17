package com.springboot.study.ch4.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

  User user1, user2, user3, user4;


  @BeforeEach
  void setUp() {
    user1 = new User(1, "Kim", 10, 10, Level.BRONZE);
    user2 = new User(2, "Lee", 20, 20, Level.BRONZE);
    user3 = new User(3, "Park", 30, 30, Level.BRONZE);
    user4 = new User(4, "Choi", 40, 40, Level.BRONZE);
  }
}