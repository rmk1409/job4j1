package ru.job4j.http.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Добавить механизм авторизации и аутентификации на фильтрах.
 * Добавить новую модель роль.
 * Каждый пользователь в системе имеют свою роль.
 * Предусмотреть. Роль - администратор. Он может добавить и редактировать любого пользователя в том числе себя.
 * В форме редактирования роли должен появиться список всех ролей.
 * <p>
 * Created by roman.pogorelov on 03.10.2019
 */
@WebFilter(urlPatterns = "*")
public class Authentication implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        if (!req.getRequestURI().contains("/login")
                && req.getSession().getAttribute("user") == null) {
            req.getRequestDispatcher("WEB-INF/view/login.jsp").forward(req, servletResponse);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
