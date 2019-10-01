package ru.job4j.http.servlet;

import ru.job4j.http.logic.ValidateService;
import ru.job4j.http.model.User;
import ru.job4j.http.persistent.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        String email = req.getParameter("email");
        User user = new User(name, login, email);
        logic.add(user);
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
