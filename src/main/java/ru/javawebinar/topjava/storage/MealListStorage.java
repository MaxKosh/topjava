package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MealListStorage implements Storage {
    private static List<Meal> storage = new CopyOnWriteArrayList<>();
    private static AtomicInteger counter = new AtomicInteger();

    static {
        storage.add(new Meal(counter.incrementAndGet(), LocalDateTime.of(2020, Month.FEBRUARY, 10, 10, 0), "Завтрак", 500));
        storage.add(new Meal(counter.incrementAndGet(), LocalDateTime.of(2020, Month.FEBRUARY, 10, 13, 0), "Обед", 1000));
        storage.add(new Meal(counter.incrementAndGet(), LocalDateTime.of(2020, Month.FEBRUARY, 10, 20, 0), "Ужин", 500));
        storage.add(new Meal(counter.incrementAndGet(), LocalDateTime.of(2020, Month.FEBRUARY, 11, 0, 0), "Еда на граничное значение", 100));
        storage.add(new Meal(counter.incrementAndGet(), LocalDateTime.of(2020, Month.FEBRUARY, 11, 10, 0), "Завтрак", 1000));
        storage.add(new Meal(counter.incrementAndGet(), LocalDateTime.of(2020, Month.FEBRUARY, 11, 13, 0), "Обед", 500));
        storage.add(new Meal(counter.incrementAndGet(), LocalDateTime.of(2020, Month.FEBRUARY, 11, 20, 0), "Ужин", 410));
    }

    @Override
    public void save(Meal meal) {
        storage.add(meal);
    }

    @Override
    public Meal get(int index) {
        return storage.get((int) getListIndex(index));
    }

    @Override
    public void update(Meal meal, int index) {
        storage.set((int) getListIndex(index), meal);
    }

    @Override
    public void delete(int index) {
        storage.remove((int) getListIndex(index));
    }

    @Override
    public List<Meal> getAll() {
        return new CopyOnWriteArrayList<>(storage);
    }

    public static int setId() {
        return counter.incrementAndGet();
    }

    private Integer getListIndex(int id) {
        for (int i = 0; i < storage.size(); i++) {
            if (id == storage.get(i).getId()) {
                System.out.println("in cycle: " + i);
                return i;
            }
        }
        return null;
    }
}
