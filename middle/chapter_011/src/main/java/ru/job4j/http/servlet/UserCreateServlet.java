package ru.job4j.http.servlet;

import ru.job4j.http.logic.ValidateService;
import ru.job4j.http.model.User;
import ru.job4j.http.persistent.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * - doGet URL /create - Открывает форму для создания нового пользователя.
 * - doPost - / - сохраняет пользователя.
 * <p>
 * Created by roman.pogorelov on 30.09.2019
 */
public class UserCreateServlet extends HttpServlet {
    private final Store logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.println(String.format("<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <title>Create new user</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "     <form action = '%s/create' method='post'>"
                + "         name: <input type='text' name='name'><br>"
                + "         login: <input type='text' name='login'><br>"
                + "         email: <input type='text' name='email'><br>"
                + "         <input type='submit'>"
                + "     </form>"
                + "</body>\n"
                + "</html>", req.getContextPath()));
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        User user = new User(name, login, email);
        logic.add(user);
        resp.sendRedirect(req.getContextPath() + "/list");
    }
}
