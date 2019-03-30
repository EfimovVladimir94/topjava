package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoMap implements MealDao {

    private Map<Integer, Meal> meals;
    private AtomicInteger idCounter;

    public MealDaoMap() {
        meals = new ConcurrentHashMap<>();
        meals.put(1, new Meal(1, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 100));
        meals.put(2, new Meal(2, LocalDateTime.of(2015, Month.MAY, 30, 15, 0), "Обед", 1500));
        meals.put(3, new Meal(3, LocalDateTime.of(2015, Month.MAY, 30, 21, 0), "Ужин", 100));
        meals.put(4, new Meal(4, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 500));
        meals.put(5, new Meal(5, LocalDateTime.of(2015, Month.MAY, 31, 15, 0), "обед", 2500));
        meals.put(6, new Meal(6, LocalDateTime.of(2015, Month.MAY, 31, 21, 0), "Ужин", 500));
        idCounter = new AtomicInteger(this.meals.size());
    }

    @Override
    public Meal add(Meal meal) {
        int id = idCounter.incrementAndGet();
        meal.setId(id);
        meals.put(id, meal);
        return new Meal(id, meal.getDateTime(), meal.getDescription(), meal.getCalories());
    }

    @Override
    public void update(int oldId, Meal meal) {
        meals.replace(oldId, meal);
    }

    @Override
    public void delete(int id) {
        meals.remove(id);
    }

    @Override
    public List<Meal> getList() {
        return new ArrayList<>(meals.values());
    }

    @Override
    public Meal getById(int oldId) {
        Meal meal = meals.get(oldId);
        return new Meal(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories());
    }
}
