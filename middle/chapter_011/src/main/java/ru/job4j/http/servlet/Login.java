package ru.job4j.http.servlet;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.http.logic.ValidateService;
import ru.job4j.http.model.User;
import ru.job4j.http.persistent.Store;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
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
        resp.sendRedirect(req.getContextPath() + "/users");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonNode jsonNode = new ObjectMapper().readTree(this.getRequestData(req));
        Optional<User> user = this.getUser(jsonNode.get("login").asText(), jsonNode.get("password").asText());
        resp.setContentType("text/html");
        String result = "false";
        if (user.isPresent()) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user.get());
            result = req.getContextPath() + "/users";
        }
        resp.getWriter().print(result);
    }

    /**
     * Returns data from request reader.
     *
     * @param req
     * @return data
     * @throws IOException
     */
    private String getRequestData(HttpServletRequest req) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        return builder.toString();
    }

    /**
     * Checks and find the concrete user by login and password.
     *
     * @param login
     * @param password
     * @return Optional of User
     */
    private Optional<User> getUser(String login, String password) {
        return this.logic.findAll()
                .stream()
                .filter(i -> Objects.equals(i.getLogin(), login) && Objects.equals(i.getPassword(), password))
                .findFirst();
    }
}
