package com.springboot.java.ch6;

import java.util.ArrayList;
import java.util.List;

public class Rating {
    int points;
    List<Review> reviews = new ArrayList<>();

    public void add(Review review) {
        reviews.add(review);
        computeRating();
    }

    private int computeRating() {
        int totalPoints =
                reviews.stream().map(Review::getPoints).reduce(0, Integer::sum);
        points = totalPoints / reviews.size();
        return points;
    }

    public static Rating average(Rating r1, Rating r2) {
        Rating combined = new Rating();
        combined.reviews = new ArrayList<>(r1.reviews);
        combined.reviews.addAll(r2.reviews);
        combined.computeRating();
        return combined;
    }
}
