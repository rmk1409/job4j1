package ru.job4j.wait;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Задание.
 * <p>
 * 1. Реализовать сервис для рассылки почты. Создайте класс EmailNotification.
 * 2. В классе будет метод emailTo(User user) - он должен через ExecutorService отправлять почту.
 * Так же добавьте метод close() - он должен закрыть pool. То есть в классе EmailNotification должно быть поле pool,
 * которые используется в emailTo и close().
 * <p>
 * 3. Модель User описывают поля username, email.
 * 4. Метод emailTo должен брать данные пользователя и подставлять в шаблон
 * subject = Notification {username} to email {email}.
 * body = Add a new event to {username}
 * <p>
 * 5. создайте метод public void send(String subject, String body, String email) - он должен быть пустой.
 * 6. Через ExecutorService создайте задачу, которая будет создавать данные для пользователя и передавать их в метод send.
 * 7. В своей папке добавьте задачу с этим именем.
 * 8. Залейте код и укажите отвественного Петра Арсентьева.
 * <p>
 * Created by roman.pogorelov on 28.09.2019
 */
public class EmailNotification {
    private static final Logger LOG = Logger.getLogger(EmailNotification.class.getName());
    private ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static final String SUBJECT_TEMPLATE = "Notification %1$s to email %2$s";
    private static final String BODY_TEMPLATE = "body = Add a new event to %1$s";

    /**
     * Send email.
     *
     * @param user
     */
    public void emailTo(User user) {
        String subject = String.format(SUBJECT_TEMPLATE, user.name, user.email);
        String body = String.format(BODY_TEMPLATE, user.name);
        this.pool.submit(() -> this.send(subject, body, user.email));
    }

    public void send(String subject, String body, String email) {

    }

    /**
     * Closes pool and waits when all tasks finish.
     */
    public void close() {
        this.pool.shutdown();
        while (!this.pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                LOG.log(Level.SEVERE, e.getMessage(), e);
            }
        }
    }
}

class User {
    String name;
    String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}