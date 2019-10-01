package ru.job4j.http.servlet;

import ru.job4j.http.logic.ValidateService;
import ru.job4j.http.persistent.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * - doGet URL  /list - открывает таблицу со всеми пользователями.
 * В каждой строке должна быть колонка с кнопками (редактировать, удалить)
 * 1. Список пользователей - таблица.
 * В таблице должны быть две кнопки. она кнопка должны открывать форму редактирования.
 * Вторая должна выполнять запрос на сервер для удаления этой заявки.
 * <p>
 * Created by roman.pogorelov on 30.09.2019
 */
public class UsersServlet extends HttpServlet {
    private final Store logic = ValidateService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.logic.delete(Long.parseLong(req.getParameter("id")));
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}
