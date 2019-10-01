package ru.job4j.http.servlet;

import ru.job4j.http.logic.ValidateService;
import ru.job4j.http.model.User;
import ru.job4j.http.persistent.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * - doGet URL /edit?id={userId} - открывает форму для редактирования с заполенными полями.
 * Редактирование - заполненная форма ввода.
 * - doPost - / - сохраняет пользователя.
 * <p>
 * Created by roman.pogorelov on 30.09.2019
 */
public class UserUpdateServlet extends HttpServlet {
    private final Store logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("user", logic.findById(Long.parseLong(req.getParameter("id"))));
        req.getRequestDispatcher("WEB-INF/view/update.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        Date createdDate = null;
        try {
            createdDate = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH)
                    .parse(req.getParameter("createdDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        User user = new User(id, name, login, email, createdDate);
        this.logic.update(user);
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
