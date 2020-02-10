package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.MealStorage;
import ru.javawebinar.topjava.storage.Storage;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);
    public static final Storage STORAGE = new MealStorage();
    private static final String LIST_USER = "/meals.jsp";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "delete":
                STORAGE.delete(getId(request));
                setAttribute(request);
                break;
            case "edit":
                Meal meal = STORAGE.get(getId(request));
                request.setAttribute("meal", meal);
            case "mealList":
                setAttribute(request);
        }
        setAttribute(request);
        request.getRequestDispatcher(LIST_USER).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mealId = request.getParameter("mealId");
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"), FORMATTER);
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));

        if (mealId == null || mealId.isEmpty()) {
            STORAGE.save(new Meal(STORAGE.size(), dateTime, description, calories));
        } else {
            int id = Integer.parseInt(mealId);
            STORAGE.update(new Meal(id, dateTime, description, calories), id);
        }
        setAttribute(request);
        request.getRequestDispatcher(LIST_USER).forward(request, response);
    }

    private int getId(HttpServletRequest request) {
        return Integer.parseInt(request.getParameter("mealId"));
    }

    private void setAttribute(HttpServletRequest request) {
        request.setAttribute("mealList", MealsUtil.filteredByStreams(STORAGE.getAll(), LocalTime.MIN, LocalTime.MAX, 2000));
    }
}
