package ru.job4j.http.persistent;

import net.jcip.annotations.ThreadSafe;
import ru.job4j.http.model.User;

import java.util.*;

/**
 * Сделать реализацию этого интерфейса MemoryStore. Сделать из него синлетон.
 * Класс MemoryStore - должен быть потокобезопастный. вы можете использовать внутри потокбезопастные коллекции.
 * <p>
 * В web.xml указать для UserServlet режим load-on-startup
 * <p>
 * http://www.xyzws.com/servletfaq/what-is-%3Cloadonstartup%3E-in-webxml-file/24
 * <p>
 * Created by roman.pogorelov on 30.09.2019
 */
@ThreadSafe
public class MemoryStore implements Store {
    private final Map<Long, User> users = Collections.synchronizedMap(new HashMap<>());

    private MemoryStore() {
    }

    private final static class Holder {

        private static final MemoryStore INSTANCE = new MemoryStore();
    }
    public static MemoryStore getInstance() {
        return Holder.INSTANCE;
    }

    public Map<Long, User> getUsers() {
        return users;
    }

    @Override
    public void add(User user) {
        this.users.put(user.getId(), user);
    }

    @Override
    public void update(User user) {
        this.add(user);
    }

    @Override
    public void delete(long id) {
        this.users.remove(id);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(this.users.values());
    }

    @Override
    public User findById(long id) {
        return this.users.get(id);
    }
}
