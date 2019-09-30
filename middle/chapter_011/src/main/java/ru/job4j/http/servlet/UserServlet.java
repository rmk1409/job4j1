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
import java.util.HashMap;
import java.util.Map;

/**
 * Необходимо создать сервлет UserServlet и определить там методы doGet doPost. Это будет слой Presentation.
 * <p>
 * Created by roman.pogorelov on 30.09.2019
 */
public class UserServlet extends HttpServlet {
    private final Store logic = ValidateService.getInstance();
    private final Map<String, Act> actions = new HashMap<>();

    /**
     * Метод doGet - должен отдавать список всех пользователей в системе.
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.println(logic.findAll());
        writer.flush();
    }

    /**
     * Метод doPost - должен  уметь делать три вещи, создавать пользователя, обновлять поля пользователя, удалять пользователя.
     * <p>
     * Давайте посмотрим пример post-запроса.
     * Создание нового пользователя.
     * action=add - ключ указывает какое действие выполнить. у нас будут три ключа add, update, delete. их мы должно обработать в doPost.
     * name=petr - ключ указывает имя пользователя.
     * на стороне сервера мы извлекаем эти данные с помощью метода request.getParameter("action")
     * <p>
     * Обновление пользователя.
     * action=update
     * id=1 - первичный ключ - генерируется один раз. по нему мы будем искать пользователя в коллекции.
     * name=ivan - новое имя.
     * <p>
     * Удаление пользователя.
     * action=delete
     * id=1 - по ключу мы удаляем пользователя.
     * <p>
     * запрос примерно будет такой
     * <p>
     * curl -d "action=add&name=Petr" -X POST http://localhost:8080/users
     * Тестирование сервлета осуществлять через Test RESTFull service
     * <p>
     * Подумайте. как тут можно применить шаблон - https://github.com/peterarsentev/code_quality_principles#2-dispatch-pattern-instead-of-multiple-if-statements-and-switch-anti-pattern
     *
     * @param req
     * @param resp
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.actions.get(req.getParameter("action")).execute(this.logic, req);
        this.doGet(req, resp);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        this.actions.put(Act.ADD, new Add());
        this.actions.put(Act.UPDATE, new Update());
        this.actions.put(Act.DELETE, new Delete());
    }
}

interface Act {
    String ADD = "add";
    String UPDATE = "update";
    String DELETE = "delete";

    void execute(Store store, HttpServletRequest req);
}

class Add implements Act {

    @Override
    public void execute(Store store, HttpServletRequest req) {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        User user = new User(name, login, email);
        store.add(user);
    }
}

class Update implements Act {

    @Override
    public void execute(Store store, HttpServletRequest req) {
        long id = Long.parseLong(req.getParameter("id"));
        User user = store.findById(id);
        user.setName(req.getParameter("name"));
        store.update(user);
    }
}

class Delete implements Act {

    @Override
    public void execute(Store store, HttpServletRequest req) {
        store.delete(Long.parseLong(req.getParameter("id")));
    }
}