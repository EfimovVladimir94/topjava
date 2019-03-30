package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MealsUtil {

    private final static int CALORIES_PER_DAY = 2000;

    public static int getCaloriesPerDay() {
        return CALORIES_PER_DAY;
    }

    public static List<MealTo> getFilteredWithExceeded(List<Meal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = mealList.stream().collect(Collectors.groupingBy(um -> um.getDateTime().toLocalDate(), Collectors.summingInt(Meal::getCalories)));

        return mealList.stream()
                .filter(um -> TimeUtil.isBetween(um.getDateTime().toLocalTime(), startTime, endTime))
                .map(um -> new MealTo(um.getId(), um.getDateTime(), um.getDescription(), um.getCalories(), caloriesSumByDate.get(um.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    public static List<MealTo> getAllMealsTo(List<Meal> meal, int caloriesPerDay) {
        return getFilteredWithExceeded(meal, LocalTime.MIN, LocalTime.MAX, caloriesPerDay);
    }

}
