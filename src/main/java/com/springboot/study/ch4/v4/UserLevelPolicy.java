package com.springboot.study.ch4.v4;

import com.springboot.study.ch4.model.Level;
import com.springboot.study.ch4.model.User;

public interface UserLevelPolicy {
    Level gradeLevel(User user);
}
