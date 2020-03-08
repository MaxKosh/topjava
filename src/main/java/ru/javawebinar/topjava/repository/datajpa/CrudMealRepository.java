package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Transactional
    @Modifying
    int deleteMealByIdAndUserId(Integer id, Integer userId);

    Meal findMealByIdAndUserId(Integer integer, Integer userId);

    List<Meal> getAllByUserIdOrderByDateTimeDesc(Integer integer);

    List<Meal> getAllByUserIdAndDateTimeGreaterThanEqualAndDateTimeLessThanOrderByDateTimeDesc(int userId, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
