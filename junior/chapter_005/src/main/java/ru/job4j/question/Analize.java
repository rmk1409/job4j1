package ru.job4j.question;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Нужно понять:
 * <p>
 * Сколько добавлено новых пользователей.
 * <p>
 * Сколько изменено пользователей. Изменённым считается объект в котором изменилось имя. а id осталось прежним.
 * <p>
 * Сколько удалено пользователей.
 * <p>
 * Created by roman.pogorelov on 06.09.2019
 */
public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        Map<Integer, User> map = previous.stream()
                .collect(Collectors.toMap(User::getId, i -> i));
        int changed = 0;
        int deleted = 0;
        for (User user : current) {
            User remove = map.remove(user.getId());
            if (Objects.isNull(remove)) {
                deleted++;
            } else if (!Objects.equals(user.name, remove.name)) {
                changed++;
            }
        }
        return new Info(map.size(), changed, deleted);
    }

    public static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Info info = (Info) o;
            return added == info.added
                    && changed == info.changed
                    && deleted == info.deleted;
        }

        @Override
        public int hashCode() {
            return Objects.hash(added, changed, deleted);
        }

        @Override
        public String toString() {
            return "Info{"
                    + "added=" + added
                    + ", changed=" + changed
                    + ", deleted=" + deleted
                    + '}';
        }
    }
}