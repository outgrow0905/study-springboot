package com.springboot.study.ch4.v4;

import com.springboot.study.ch4.model.Level;
import com.springboot.study.ch4.model.User;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class UserLevelDefaultPolicy implements UserLevelPolicy {
  @Override
  public Level gradeLevel(User user) {
    Level nextLevel;

    if (user.getVisit() >= 30 && user.getLike() >= 30) {
      nextLevel = Level.GOLD;
    }
    else if (user.getVisit() >= 20 && user.getLike() >= 20) {
      nextLevel = Level.SILVER;
    }
    else {
      nextLevel = Level.BRONZE;
    }

    return nextLevel;
  }

}
