package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static int MEAL_ID = START_SEQ + 2;

    public static final Meal MEAL_1 = new Meal(MEAL_ID++, LocalDateTime.of(2020, Month.FEBRUARY, 15, 15, 15), "обед", 1000);
    public static final Meal MEAL_2 = new Meal(MEAL_ID++, LocalDateTime.of(2020, Month.FEBRUARY, 16, 10, 10), "завтрак", 500);
    public static final Meal MEAL_3 = new Meal(MEAL_ID++, LocalDateTime.of(2020, Month.FEBRUARY, 16, 18, 18), "ужин", 1500);
    public static final Meal MEAL_4 = new Meal(MEAL_ID++, LocalDateTime.of(2020, Month.FEBRUARY, 16, 8, 0), "завтрак", 100);

    public static final Meal MEAL_5 = new Meal(MEAL_ID++, LocalDateTime.of(2020, Month.JANUARY, 15, 9, 15), "завтрак", 600);
    public static final Meal MEAL_6 = new Meal(MEAL_ID++, LocalDateTime.of(2020, Month.JANUARY, 15, 14, 15), "обед", 800);
    public static final Meal MEAL_7 = new Meal(MEAL_ID++, LocalDateTime.of(2020, Month.JANUARY, 15, 18, 20), "ужин", 600);
    public static final Meal MEAL_8 = new Meal(MEAL_ID++, LocalDateTime.of(2020, Month.FEBRUARY, 16, 10, 10), "завтрак", 500);


    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2020, Month.FEBRUARY, 24, 23, 20), "поздний ужин", 1000);
    }

    public static Meal getUpdated() {
        Meal updated = MEAL_5;
        updated.setDescription("поздний завтрак");
        updated.setDateTime(LocalDateTime.of(2020, Month.FEBRUARY, 16, 11, 0));
        updated.setCalories(500);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
