package ru.job4j.http.persistent;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.http.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TODO Description
 * Created by roman.pogorelov on 01.10.2019
 */
public class DBStore implements Store {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final Logger LOG = Logger.getLogger(DBStore.class.getName());

    private DBStore() {
        Properties properties = new Properties();
        try (InputStream in = DBStore.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
        SOURCE.setDriverClassName(properties.getProperty("driver-class-name"));
        SOURCE.setUrl(properties.getProperty("url"));
        SOURCE.setUsername(properties.getProperty("username"));
        SOURCE.setPassword(properties.getProperty("password"));
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    public static Store getInstance() {
        return Holder.INSTANCE;
    }

    private final static class Holder {
        private final static DBStore INSTANCE = new DBStore();
    }

    @Override
    public void add(User user) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("INSERT INTO users(name, login, email, createddate) VALUES(?,?,?,?);")
        ) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setDate(4, new java.sql.Date(user.getCreateDate().getTime()));
            st.executeUpdate();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    @Override
    public void update(User user) {
        try (Connection con = SOURCE.getConnection();
             PreparedStatement st = con.prepareStatement("UPDATE users SET name = ?, login = ?, email = ? WHERE ID = ?")) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setLong(4, user.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    @Override
    public void delete(long id) {
        try (Connection con = SOURCE.getConnection();
             PreparedStatement st = con.prepareStatement("DELETE FROM users WHERE ID = ?")) {
            st.setLong(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection con = SOURCE.getConnection();
             PreparedStatement st = con.prepareStatement("SELECT * FROM users")
        ) {
            ResultSet set = st.executeQuery();
            while (set.next()) {
                long id = set.getLong("id");
                String name = set.getString("name");
                String login = set.getString("login");
                String email = set.getString("email");
                java.util.Date date = new java.util.Date(set.getDate("createddate").getTime());
                User user = new User(id, name, login, email, date);
                users.add(user);
            }
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
        return users;
    }

    @Override
    public User findById(long id) {
        User user = null;
        try (Connection con = SOURCE.getConnection();
             PreparedStatement st = con.prepareStatement("SELECT * FROM users WHERE ID = ?")) {
            st.setLong(1, id);
            ResultSet set = st.executeQuery();
            if (set.next()) {
                String name = set.getString("name");
                String login = set.getString("login");
                String email = set.getString("email");
                java.util.Date date = new java.util.Date(set.getDate("createddate").getTime());
                user = new User(id, name, login, email, date);
            }
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
        return user;
    }
}
