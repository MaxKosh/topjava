package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UsersUtil {

    public static final List<User> USERS = Arrays.asList(
            new User(null, "John Conner", "johnconner@yandex.ru", "myPass", Role.ROLE_USER),
            new User(null, "T1000", "t1000@yandex.ru", "myPass", Role.ROLE_USER)
    );
}
