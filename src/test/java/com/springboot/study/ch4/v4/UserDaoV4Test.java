package com.springboot.study.ch4.v4;

import static org.junit.jupiter.api.Assertions.*;

import com.springboot.study.ch4.model.Level;
import com.springboot.study.ch4.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserDaoV4Test {

  @Autowired
  private UserDaoV4 userDaoV4;

  @BeforeEach
  void setUp() {
      userDaoV4.deleteAll();
      assertEquals(0, userDaoV4.selectCount());
      userDaoV4.insertUser(new User(1, "Kim", 10, 10, Level.BRONZE));
      userDaoV4.insertUser(new User(2, "Lee", 20, 20, Level.SILVER));
      userDaoV4.insertUser(new User(3, "Park", 30, 30, Level.GOLD));
      userDaoV4.insertUser(new User(4, "Choi", 40, 40, Level.GOLD));
      assertEquals(4, userDaoV4.selectCount());
  }

  @Test
  void insert_and_select() {
      userDaoV4.deleteUser(4);
      userDaoV4.insertUser(new User(4, "Choi", 40, 40, Level.GOLD));
      User user = userDaoV4.selectUser(4);
      assertEquals("Choi", user.getName());
      assertEquals(40, user.getVisit());
      assertEquals(40, user.getLike());
      assertEquals(Level.GOLD, user.getLevel());
  }

  @Test
  void insert_update_and_select() {
    userDaoV4.deleteUser(4);
    userDaoV4.insertUser(new User(4, "Choi", 40, 40, Level.GOLD));
    userDaoV4.updateLevel(4, Level.SILVER);
    User user = userDaoV4.selectUser(4);
    assertEquals(Level.SILVER, user.getLevel());
  }

  @Test
  void delete_all() {
    userDaoV4.deleteAll();
    assertEquals(0, userDaoV4.selectCount());

    userDaoV4.insertUser(new User(4, "Choi", 20, 20, Level.GOLD));
    assertEquals(1, userDaoV4.selectCount());
  }
}