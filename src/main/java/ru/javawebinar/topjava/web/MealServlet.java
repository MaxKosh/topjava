package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.MealListStorage;
import ru.javawebinar.topjava.storage.Storage;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
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
    private Storage mealListStorage;
    private static final Logger LOG = getLogger(MealServlet.class);
    private static final String LIST_USER = "/meals.jsp";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        mealListStorage = new MealListStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("redirect to ./meals");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "delete":
                mealListStorage.delete(getIdFromRequest(request));
                response.sendRedirect("meals");
                return;
            case "edit":
                Meal meal = mealListStorage.get(getIdFromRequest(request));
                request.setAttribute("meal", meal);
                break;
            default:
                setAttribute(request);

        }
        request.getRequestDispatcher(LIST_USER).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        LOG.debug("POST request received");
        String mealId = request.getParameter("id");
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"), FORMATTER);
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));

        if (mealId == null || mealId.isEmpty()) {
            mealListStorage.save(new Meal(MealListStorage.setId(), dateTime, description, calories));
        } else {
            int id = Integer.parseInt(mealId);
            mealListStorage.update(new Meal(MealListStorage.setId(), dateTime, description, calories), id);
        }
        response.sendRedirect("meals");
    }

    private int getIdFromRequest(HttpServletRequest request) {
        return Integer.parseInt(request.getParameter("id"));
    }

    private void setAttribute(HttpServletRequest request) {
        request.setAttribute("mealList",
                MealsUtil.filteredByStreams(mealListStorage.getAll(), LocalTime.MIN, LocalTime.MAX, 2000));
    }
}
