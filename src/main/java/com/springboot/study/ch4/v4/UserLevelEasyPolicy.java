package com.springboot.study.ch4.v4;

import com.springboot.study.ch4.model.Level;
import com.springboot.study.ch4.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserLevelEasyPolicy implements UserLevelPolicy {


  @Override
  public Level gradeLevel(User user) {
        return Level.GOLD;
    }
}
