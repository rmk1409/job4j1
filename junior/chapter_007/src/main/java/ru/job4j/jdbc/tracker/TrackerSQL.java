package ru.job4j.jdbc.tracker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.Item;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * TODO Description
 * Created by roman.pogorelov on 12.09.2019
 */
public class TrackerSQL implements ITracker, AutoCloseable {
    private static final Logger LOGGER = LogManager.getLogger(TrackerSQL.class);

    private Connection connection;

    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    @Override
    public Item add(Item item) {
        int result = 0;
        try (PreparedStatement statement = this.connection.prepareStatement("insert into item(id, name, description) values(?, ?, ?)")) {
            item.setId(this.generateId());
            statement.setString(1, item.getId());
            statement.setString(2, item.getName());
            statement.setString(3, item.getDescription());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result == 1 ? item : null;
    }

    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }

    @Override
    public boolean replace(String id, Item item) {
        int result = 0;
        try (PreparedStatement st = this.connection.prepareStatement("update item set (name, description) = (?, ?) where id = ?")) {
            st.setString(3, id);
            st.setString(1, item.getName());
            st.setString(2, item.getDescription());
            result = st.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result == 1;
    }

    @Override
    public boolean delete(String id) {
        int result = 0;
        try (PreparedStatement st = this.connection.prepareStatement("delete from item where id = ?")) {
            st.setString(1, id);
            result = st.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result == 1;
    }

    @Override
    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        try (Statement st = this.connection.createStatement(); ResultSet set = st.executeQuery("select * from item")) {
            while (set.next()) {
                Item item = new Item("");
                item.setId(set.getString("id"));
                item.setName(set.getString("name"));
                item.setDescription(set.getString("description"));
                result.add(item);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<Item> findByName(String name) {
        List<Item> result = new ArrayList<>();
        try (PreparedStatement st = this.connection.prepareStatement("select * from item where name = ?")) {
            st.setString(1, name);
            ResultSet set = st.executeQuery();
            while (set.next()) {
                Item item = new Item(name);
                item.setId(set.getString("id"));
                item.setDescription(set.getString("description"));
                result.add(item);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Item findById(String id) {
        Item result = null;
        try (PreparedStatement st = this.connection.prepareStatement("select * from item where id = ?")) {
            st.setString(1, id);
            ResultSet set = st.executeQuery();
            while (set.next()) {
                result = new Item("");
                result.setId(set.getString("id"));
                result.setName(set.getString("name"));
                result.setDescription(set.getString("description"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        this.connection.close();
    }

    public void clearDB() {
        try {
            Statement st = this.connection.createStatement();
            st.execute("delete from item");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
