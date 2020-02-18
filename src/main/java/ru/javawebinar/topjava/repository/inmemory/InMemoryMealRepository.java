package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);

    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    private static final Comparator<Meal> MEAL_COMPARATOR =
            Comparator.comparing(Meal::getDateTime).reversed();

    {
        MealsUtil.MEALS_1.forEach(meal -> InMemoryMealRepository.this.save(meal, 1));
        MealsUtil.MEALS_2.forEach(meal -> InMemoryMealRepository.this.save(meal, 2));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        log.info("save {}", meal);
        Map<Integer, Meal> map = repository.get(userId);
        if (map == null) {
            map = new ConcurrentHashMap<>();
        }
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            map.put(meal.getId(), meal);
            repository.put(userId, map);
            return meal;
        }
        return map.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        log.info("delete {}", id);
        Map<Integer, Meal> map = repository.get(userId);
        return map != null && map.remove(id, map.get(id));
    }

    @Override
    public Meal get(int id, int userId) {
        log.info("get {}", id);
        Map<Integer, Meal> map = repository.get(userId);
        return map != null ? map.get(id) : null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        log.info("getAll");
        return getSortedList(userId, meal -> true);
    }

    @Override
    public List<Meal> getAllFiltered(int userId, LocalDate startDate, LocalDate endDate) {
        log.info("getAll with date filter");
        return getSortedList(userId, meal -> DateTimeUtil.dateTimeFilter(meal.getDate(), startDate, endDate));
    }

    private List<Meal> getSortedList(int userId, Predicate<Meal> filter) {
        Map<Integer, Meal> map = repository.get(userId);
        return map != null ? map.values().stream()
                .filter(filter)
                .sorted(MEAL_COMPARATOR)
                .collect(Collectors.toList())
                : Collections.emptyList();
    }
}

