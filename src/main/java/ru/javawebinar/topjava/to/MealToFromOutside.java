package ru.javawebinar.topjava.to;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

public class MealToFromOutside extends BaseTo {
    @NotNull
    private LocalDateTime dateTime;

    @NotBlank
    @Size(min = 2, max = 100)
    private String description;

    @Range(min = 10, max = 5000, message = "value must between 10 and 5000 calories")
    @NotNull
    private Integer calories;

    public MealToFromOutside(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime localDateTime) {
        this.dateTime = localDateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MealToFromOutside that = (MealToFromOutside) o;
        return Objects.equals(calories, that.calories) &&
                Objects.equals(id, that.id) &&
                Objects.equals(dateTime, that.dateTime) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateTime, description, calories);
    }

    @Override
    public String toString() {
        return "MealToFromOutside{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }

}

