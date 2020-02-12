package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MealMapStorage implements Storage {
    private static Map<Integer, Meal> storage = new ConcurrentHashMap<>();
    private static AtomicInteger counter = new AtomicInteger();

    static {
        Meal meal_1 = new Meal(counter.incrementAndGet(), LocalDateTime.of(2020, Month.FEBRUARY, 10, 10, 0), "Завтрак", 500);
        Meal meal_2 = new Meal(counter.incrementAndGet(), LocalDateTime.of(2020, Month.FEBRUARY, 10, 13, 0), "Обед", 1000);
        Meal meal_3 = new Meal(counter.incrementAndGet(), LocalDateTime.of(2020, Month.FEBRUARY, 10, 20, 0), "Ужин", 500);
        Meal meal_4 = new Meal(counter.incrementAndGet(), LocalDateTime.of(2020, Month.FEBRUARY, 11, 0, 0), "Еда на граничное значение", 100);
        Meal meal_5 = new Meal(counter.incrementAndGet(), LocalDateTime.of(2020, Month.FEBRUARY, 11, 10, 0), "Завтрак", 1000);
        Meal meal_6 = new Meal(counter.incrementAndGet(), LocalDateTime.of(2020, Month.FEBRUARY, 11, 13, 0), "Обед", 500);
        Meal meal_7 = new Meal(counter.incrementAndGet(), LocalDateTime.of(2020, Month.FEBRUARY, 11, 20, 0), "Ужин", 410);
        storage.put(meal_1.getId(), meal_1);
        storage.put(meal_2.getId(), meal_2);
        storage.put(meal_3.getId(), meal_3);
        storage.put(meal_4.getId(), meal_4);
        storage.put(meal_5.getId(), meal_5);
        storage.put(meal_6.getId(), meal_6);
        storage.put(meal_7.getId(), meal_7);
    }

    @Override
    public void save(Meal meal) {
        Meal newMeal = new Meal(counter.incrementAndGet(), meal.getDateTime(), meal.getDescription(), meal.getCalories());
        storage.put(newMeal.getId(), newMeal);
    }

    @Override
    public Meal get(int index) {
        return storage.get(index);
    }

    @Override
    public void update(Meal meal, int index) {
        storage.replace(index, meal);
    }

    @Override
    public void delete(int index) {
        storage.remove(index);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(storage.values());
    }
}
