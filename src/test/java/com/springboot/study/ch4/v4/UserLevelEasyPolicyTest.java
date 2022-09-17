package com.springboot.study.ch4.v4;

import static org.junit.jupiter.api.Assertions.*;

import com.springboot.study.ch4.model.Level;
import com.springboot.study.ch4.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserLevelEasyPolicyTest {

  User user1, user2, user3, user4;

  @Autowired
  private UserLevelEasyPolicy userLevelEasyPolicy;

  @BeforeEach
  void setUp() {
    user1 = new User(1, "Kim", 10, 10, Level.BRONZE);
    user2 = new User(2, "Lee", 20, 20, Level.BRONZE);
    user3 = new User(3, "Park", 30, 30, Level.BRONZE);
    user4 = new User(4, "Choi", 40, 40, Level.BRONZE);
  }


  @Test
  void gradeLevel() {
    assertEquals(Level.GOLD, userLevelEasyPolicy.gradeLevel(user1));
    assertEquals(Level.GOLD, userLevelEasyPolicy.gradeLevel(user2));
    assertEquals(Level.GOLD, userLevelEasyPolicy.gradeLevel(user3));
    assertEquals(Level.GOLD, userLevelEasyPolicy.gradeLevel(user4));
  }
}