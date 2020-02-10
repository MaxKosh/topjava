package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.util.List;

public interface Storage {
    void clear();

    void save(Meal meal);

    Meal get(int index);

    void update(Meal meal, int index);

    void delete(int index);

    List<Meal> getAll();
}
