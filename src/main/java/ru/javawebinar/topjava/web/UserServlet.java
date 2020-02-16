package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.user.ProfileRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class UserServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);
    ProfileRestController controller;
    User user;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        controller = ContextListener.appCtx.getBean(ProfileRestController.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward to users");
        String id = request.getParameter("id");

        if (id == null) {
            request.getRequestDispatcher("/users.jsp").forward(request, response);
        } else {
            user = controller.get(Integer.parseInt(id));
            SecurityUtil.setUserId(user.getId());
        }
        response.sendRedirect("/topjava/meals");
    }
}
