package com.restaurante.feedback.entities;

import java.io.Serializable;

public class Feedback implements Serializable {
    private int id;
    private final int dishId;
    private final String user;
    private final int rating;
    private final String comment;

    public Feedback(int dishId, String user, int rating, String comment) {
        this.dishId = dishId;
        this.user = user;
        this.rating = rating;
        this.comment = comment;
    }

    public Feedback(int id, int dishId, String user, int rating, String comment) {
        this.id = id;
        this.dishId = dishId;
        this.user = user;
        this.rating = rating;
        this.comment = comment;
    }

    public int getId() { return id; }
    public int getDishId() { return dishId; }
    public String getUser() { return user; }
    public int getRating() { return rating; }
    public String getComment() { return comment; }

    public void setId(int id) { this.id = id; }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", dishId=" + dishId +
                ", user='" + user + '\'' +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                '}';
    }
}
