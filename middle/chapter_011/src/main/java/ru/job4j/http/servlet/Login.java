package ru.job4j.http.servlet;

import ru.job4j.http.logic.ValidateService;
import ru.job4j.http.model.User;
import ru.job4j.http.persistent.Store;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by roman.pogorelov on 03.10.2019
 */
@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet {
    private final Store logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Optional<User> user = logic.findAll()
                .stream()
                .filter(i -> Objects.equals(i.getLogin(), login) && Objects.equals(i.getPassword(), password)).findFirst();
        if (user.isPresent()) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user.get());
            session.removeAttribute("wrongCreds");
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            req.getSession().setAttribute("wrongCreds", true);
            this.doGet(req, resp);
        }
    }
}
