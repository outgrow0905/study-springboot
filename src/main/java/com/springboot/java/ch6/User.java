package com.springboot.java.ch6;

import java.util.List;

public class User {
    String name;
    int age;

    Rating rating;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
        this.rating = new Rating();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }
}
