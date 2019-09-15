package ru.job4j.jdbc.tracker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO Description
 * Created by roman.pogorelov on 12.09.2019
 */
public class TrackerSQL implements ITracker, AutoCloseable {
    private static final Logger LOGGER = LogManager.getLogger(TrackerSQL.class);

    private final Connection connection;

    public TrackerSQL(Connection connection) {
        this.connection = connection;
    }

    public boolean init() {
        try {
            var script = "CREATE TABLE IF NOT EXISTS item("
                    + "    id serial primary key,"
                    + "    name character varying(50) ,"
                    + "    description character varying(50)"
                    + ")";
            Statement st = this.connection.createStatement();
            st.execute(script);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    @Override
    public Item add(Item item) {
        int result = 0;
        try (PreparedStatement statement = this.connection.prepareStatement("insert into item(name, description) values(?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getName());
            statement.setString(2, item.getDescription());
            result = statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    item.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result == 1 ? item : null;
    }

    @Override
    public boolean replace(Long id, Item item) {
        int result = 0;
        try (PreparedStatement st = this.connection.prepareStatement("update item set (name, description) = (?, ?) where id = ?")) {
            st.setLong(3, id);
            st.setString(1, item.getName());
            st.setString(2, item.getDescription());
            result = st.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result == 1;
    }

    @Override
    public boolean delete(Long id) {
        int result = 0;
        try (PreparedStatement st = this.connection.prepareStatement("delete from item where id = ?")) {
            st.setLong(1, id);
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
                item.setId(set.getLong("id"));
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
                item.setId(set.getLong("id"));
                item.setDescription(set.getString("description"));
                result.add(item);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Item findById(Long id) {
        Item result = null;
        try (PreparedStatement st = this.connection.prepareStatement("select * from item where id = ?")) {
            st.setLong(1, id);
            ResultSet set = st.executeQuery();
            while (set.next()) {
                result = new Item("");
                result.setId(set.getLong("id"));
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
        if (this.connection != null) {
            this.connection.close();
        }
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
