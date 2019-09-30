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
import java.util.List;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        List<User> users = logic.findAll();
        StringBuilder builder = new StringBuilder();
        users.forEach(i ->
                builder.append(String.format("<tr>\n"
                        + "     <td>%1$s</td>\n"
                        + "     <td>"
                        + "         <form action='%2$s/edit' method = 'get'>"
                        + "             <input type = 'hidden' name = 'id' value='%3$d'>"
                        + "             <button>edit</button>"
                        + "         </form>"
                        + "     </td>\n"
                        + "     <td>"
                        + "         <form action='%2$s/list' method = 'post'>"
                        + "             <input type = 'hidden' name = 'id' value='%3$d'>"
                        + "             <button>delete</button>"
                        + "         </form>"
                        + "     </td>\n"
                        + "</tr>", i.toString(), req.getContextPath(), i.getId())));
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.println("<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <title>Show all users</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "     <table>\n"
                + "         " + builder.toString()
                + "     </table>\n"
                + "</body>\n"
                + "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.logic.delete(Long.parseLong(req.getParameter("id")));
        resp.sendRedirect(req.getContextPath() + "/list");
    }
}
