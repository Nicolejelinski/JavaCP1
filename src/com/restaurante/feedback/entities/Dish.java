package com.restaurante.feedback.entities;

import java.io.Serializable;
import java.util.UUID;

public class Dish implements Serializable {
    private int id;
    private final String name;
    private final String description;

    public Dish(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Dish(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }

    public void setId(int id) { this.id = id; }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
