package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.MealStorage;
import ru.javawebinar.topjava.storage.Storage;

import java.time.LocalDateTime;
import java.time.Month;

public class MainStorage {
    public static  Storage storage = new MealStorage();

    static final Meal meal_1;
    static final Meal meal_2;
    static final Meal meal_3;
    static final Meal meal_4;
    static final Meal meal_5;
    static final Meal meal_6;
    static final Meal meal_7;

    static {
        meal_1 = new Meal(1, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
        meal_2 = new Meal(2, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000);
        meal_3 = new Meal(3, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500);
        meal_4 = new Meal(4, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100);
        meal_5 = new Meal(5, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000);
        meal_6 = new Meal(6, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500);
        meal_7 = new Meal(7, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410);
    }

    public void testInitStorage() {
        storage.save(meal_1);
        storage.save(meal_2);
        storage.save(meal_3);
        storage.save(meal_4);
        storage.save(meal_5);
        storage.save(meal_6);
        storage.save(meal_7);
    }
}
