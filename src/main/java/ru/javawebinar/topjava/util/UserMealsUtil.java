package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 7, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 11, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo_1 = filteredByCycles(meals, LocalTime.of(1, 0), LocalTime.of(23, 0), 2000);
        mealsTo_1.forEach(System.out::println);

        System.out.println(" ");

        List<UserMealWithExcess> mealsTo_2 = filteredByStreams(meals, LocalTime.of(1, 0), LocalTime.of(23, 0), 2000);
        mealsTo_2.forEach(System.out::println);

//        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> tempMap = new HashMap<>();
        for (UserMeal userMeal : meals) {
            LocalDate localDate = userMeal.getDateTime().toLocalDate();
            int calories = userMeal.getCalories();
            tempMap.merge(localDate, calories, Integer::sum);
        }

        List<UserMealWithExcess> userMealWithExcess = new ArrayList<>();
        for (UserMeal userMeal : meals) {
            int calories = tempMap.get(userMeal.getDateTime().toLocalDate());
            LocalTime lt = userMeal.getDateTime().toLocalTime();
            if (TimeUtil.isBetweenInclusive(lt, startTime, endTime)) {
                userMealWithExcess.add(new UserMealWithExcess(userMeal.getDateTime(), userMeal.getDescription(),
                        userMeal.getCalories(), calories > caloriesPerDay));
            }
        }
        return userMealWithExcess;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> tempMap = meals.stream()
                .collect(Collectors.groupingBy(UserMeal -> UserMeal.getDateTime().toLocalDate(),
                        Collectors.summingInt(UserMeal::getCalories)));

        return meals.stream()
                .filter(p -> TimeUtil.isBetweenInclusive(p.getDateTime().toLocalTime(), startTime, endTime))
                .map(p -> new UserMealWithExcess(p.getDateTime(), p.getDescription(),
                        p.getCalories(), tempMap.get(p.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }
}