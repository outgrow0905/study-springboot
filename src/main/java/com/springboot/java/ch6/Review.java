package com.springboot.java.ch6;

public class Review {
    private int points;
    private String review;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Review(int points, String review) {
        this.points = points;
        this.review = review;
    }
}
