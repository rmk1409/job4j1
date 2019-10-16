package ru.job4j.clientlanguage.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.clientlanguage.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 1. Создайте сервлет, который будет принимать JSON объект.
 * Для этого нужно в сервлете прочитать content.
 * BufferedReader reader = request.getReader();
 * Преобразовать его в StringBuilder.
 * Далее с помощью библиотеки jakson преобразовать в объект.
 * Данные нужно хранить в памяти в карте ConcurrencyHashMap.
 * <p>
 * 2. Через JQuery в задании #58522 создайте событие на нажатие кнопки, которые будет создавать вызов на сервер.
 * $.ajax({
 * type: "POST",
 * url: url,
 * data: data,
 * success: success,
 * dataType: dataType
 * });
 * <p>
 * 3. Когда данные пришли на клиент, нужно добавить новую запись в таблицу.
 */
@WebServlet(urlPatterns = "/json")
public class JSONHandler extends HttpServlet {
    private ConcurrentHashMap<String, User> storage = new ConcurrentHashMap<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(builder.toString(), User.class);
        this.storage.put(user.getName(), user);
        resp.setContentType("text/json");
        resp.getWriter().println(objectMapper.writeValueAsString(user));
    }
}
