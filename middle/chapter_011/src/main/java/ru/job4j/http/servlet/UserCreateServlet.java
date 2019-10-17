package ru.job4j.http.servlet;

import ru.job4j.http.logic.ValidateService;
import ru.job4j.http.model.User;
import ru.job4j.http.persistent.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * - doGet URL /create - Открывает форму для создания нового пользователя.
 * - doPost - / - сохраняет пользователя.
 * <p>
 * Created by roman.pogorelov on 30.09.2019
 */
public class UserCreateServlet extends HttpServlet {
    private final Store logic = ValidateService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        String city = req.getParameter("city");
        String role = req.getParameter("role");
        User user = new User(0L, name, login, password, email, role, new Date(), country, city);
        logic.add(user);
        resp.sendRedirect(req.getContextPath() + "/users");
    }
}
