package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    private static final Comparator<Meal> MEAL_COMPARATOR =
            Comparator.comparing(Meal::getDateTime).reversed();

    {
        MealsUtil.MEALS.forEach(meal -> meal.setUserId(1));
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id) != null;
    }

    @Override
    public Meal get(int id) {
        return repository.get(id);
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return repository.values().stream()
                .filter(meal -> meal.getUserId() == userId)
                .sorted(MEAL_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Meal> getAll(int userId, LocalDate startDate, LocalDate endDate) {
        return repository.values().stream()
                .filter(meal -> meal.getUserId() == userId)
                .filter(meal -> DateTimeUtil.isBetweenDate(meal.getDate(), startDate, endDate))
                .sorted(MEAL_COMPARATOR)
                .collect(Collectors.toList());
    }
}

