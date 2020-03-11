package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;

import java.util.Collections;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaUserServiceTest extends AbstractUserServiceTest {

    @Test
    public void getWithMeals() {
        User user = service.getWithMeals(ADMIN_ID);
        USER_MATCHER.assertMatch(user, ADMIN);
        MEAL_MATCHER.assertMatch(user.getMeals(), ADMIN_MEAL1, ADMIN_MEAL2);
    }

    @Test
    public void getWithoutMeals() {
        User user = service.getWithMeals(USER_WITHOUT_MEAL_ID);
        USER_MATCHER.assertMatch(user, USER_WITHOUT_MEAL);
        MEAL_MATCHER.assertMatch(user.getMeals(), Collections.EMPTY_LIST);
    }
}