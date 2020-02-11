package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MealStorage implements Storage {
    private List<Meal> storage = new CopyOnWriteArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void save(Meal meal) {
        storage.add(meal);
    }

    @Override
    public Meal get(int index) {
        return storage.get(index);
    }

    @Override
    public void update(Meal meal, int index) {
        storage.set(index, meal);
    }

    @Override
    public void delete(int index) {
        storage.remove(index);
    }

    @Override
    public List<Meal> getAll() {
        return storage;
    }

    @Override
    public int size() {
        return storage.size();
    }
}
