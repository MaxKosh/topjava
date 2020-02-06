package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.meals;

public class MealController extends HttpServlet {
    private static final Logger log = getLogger(MealController.class);
    //private static String INSERT_OR_EDIT = "/meals.jsp";
    private static String LIST_USER = "/meals.jsp";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");

        List<MealTo> mealList = MealsUtil.filteredByStreams(MealsUtil.meals, LocalTime.MIN, LocalTime.MAX, 2000);

        if (action.equalsIgnoreCase("delete")) {
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            mealList.remove(mealId-1);
            forward = LIST_USER;
            request.setAttribute("mealList", mealList);
        } else if (action.equalsIgnoreCase("listUser")) {
            forward = LIST_USER;
            request.setAttribute("mealList", mealList);
        }


        /*else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int userId = Integer.parseInt(request.getParameter("userId"));
            //User user = dao.getUserById(userId);
            request.setAttribute("user", user);*/
          /*else {
            forward = INSERT_OR_EDIT;
        }*/

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
}
