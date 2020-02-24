package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.MealTestData.assertMatch;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void create() {
        Meal newMeal = getNew();
        Meal created = service.create(newMeal, USER_ID);
        Integer newId = created.getId();
        newMeal.setId(newId);
        assertMatch(created, newMeal);
        assertMatch(service.get(newId, USER_ID), newMeal);
    }

    @Test(expected = NotFoundException.class)
    public void delete() {
        service.delete(MEAL_2.getId(), USER_ID);
        service.get(MEAL_2.getId(), USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void deleteWithBadUserId() {
        service.delete(MEAL_2.getId(), ADMIN_ID);
    }

    @Test
    public void get() {
        Meal meal = service.get(MEAL_1.getId(), USER_ID);
        assertMatch(meal, MEAL_1);
    }

    @Test(expected = NotFoundException.class)
    public void getWithBadUserId() {
        service.get(MEAL_1.getId(), ADMIN_ID);
    }

    @Test
    public void update() {
        Meal updated = getUpdated();
        service.update(updated, ADMIN_ID);
        assertMatch(service.get(updated.getId(), ADMIN_ID), updated);
    }

    @Test(expected = NotFoundException.class)
    public void updateWithBadUserId() {
        Meal updated = getUpdated();
        service.update(updated, USER_ID);
    }

    @Test
    public void getBetweenHalfOpen() {
        List<Meal> bho = service.getBetweenHalfOpen(LocalDate.of(2020, Month.FEBRUARY, 14),
                LocalDate.of(2020, Month.FEBRUARY, 15), USER_ID);
        List<Meal> testBho = Arrays.asList(MEAL_1);
        assertMatch(bho, testBho);
    }

    @Test
    public void getAll() {
        List<Meal> all = service.getAll(USER_ID);
        List<Meal> testAll = Arrays.asList(MEAL_3, MEAL_2, MEAL_4, MEAL_1);
        assertMatch(all, testAll);
    }


}
