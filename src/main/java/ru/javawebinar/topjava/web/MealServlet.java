package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    MealRestController controller;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        controller = ContextListener.appCtx.getBean(MealRestController.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        if (id.equals("")) {
            controller.create(new Meal(LocalDateTime.parse(request.getParameter("dateTime")),
                    request.getParameter("description"),
                    Integer.parseInt(request.getParameter("calories"))));
        } else {
            int mealId = Integer.parseInt(id);
            controller.update(new Meal(mealId,
                    LocalDateTime.parse(request.getParameter("dateTime")),
                    request.getParameter("description"),
                    Integer.parseInt(request.getParameter("calories"))), mealId);

            //log.info(meal.isNew() ? "Create {}" : "Update {}", meal);
        }
        response.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LocalDate startLocalDate;
        LocalDate endLocalDate;
        LocalTime startLocalTime;
        LocalTime endLocalTime;
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");

        if (startDate != null && !startDate.equals("")) {
            startLocalDate = LocalDate.parse(startDate, DATE_FORMATTER);
        } else {
            startLocalDate = LocalDate.MIN;
        }
        if (endDate != null && !endDate.equals("")) {
            endLocalDate = LocalDate.parse(endDate, DATE_FORMATTER);
        } else {
            endLocalDate = LocalDate.MAX;
        }
        if (startTime != null && !startTime.equals("")) {
            startLocalTime = LocalTime.parse(startTime);
        } else {
            startLocalTime = LocalTime.MIN;
        }
        if (endTime != null && !endTime.equals("")) {
            endLocalTime = LocalTime.parse(endTime);
        } else {
            endLocalTime = LocalTime.MAX;
        }

        String action = request.getParameter("action");
        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                log.info("Delete {}", id);
                controller.delete(id);
                response.sendRedirect("meals");
                break;
            case "create":
            case "update":
                final Meal meal = "create".equals(action) ?
                        new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                        controller.get(getId(request));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/mealForm.jsp").forward(request, response);
                break;
            case "all":
            default:
                log.info("getAll");
                request.setAttribute("meals",
                        controller.getAll(startLocalDate, endLocalDate, startLocalTime, endLocalTime));
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
