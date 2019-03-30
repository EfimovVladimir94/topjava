package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

public class MealTo {
    private int id;
    private final LocalDateTime dateTime;
    private final String description;
    private final int calories;
    private final boolean excees;

    public MealTo(int id, LocalDateTime dateTime, String description, int calories, boolean excees) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excees = excees;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExcees() {
        return excees;
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", excees=" + excees +
                '}';
    }
}
