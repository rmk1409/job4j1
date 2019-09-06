package ru.job4j.question;

import java.util.List;
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
        int deleted = this.getDeleted(previous, current);
        int added = this.getAdded(current.size(), previous.size(), deleted);
        int changed = this.getChanged(current, previous);
        return new Info(added, changed, deleted);
    }

    private int getDeleted(List<User> previous, List<User> current) {
        return (int) previous.stream()
                .filter(i -> !current.contains(i))
                .count();
    }

    private int getAdded(int current, int previous, int deleted) {
        return current - previous + deleted;
    }

    private int getChanged(List<User> current, List<User> previous) {
        int result = 0;
        List<User> collect = current.stream()
                .filter(previous::contains)
                .collect(Collectors.toList());
        for (User user : collect) {
            for (User previousUser : previous) {
                if (Objects.equals(user.id, previousUser.id)) {
                    if (!Objects.equals(user.name, previousUser.name)) {
                        result++;
                    }
                    break;
                }
            }
        }
        return result;
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
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