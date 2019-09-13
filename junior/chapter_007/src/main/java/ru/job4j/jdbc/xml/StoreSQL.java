package ru.job4j.jdbc.xml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Uses SQLite, has the basic crud operations.
 * <p>
 * Created by roman.pogorelov on 12.09.2019
 */
public class StoreSQL implements AutoCloseable {
    private static final Logger LOGGER = LogManager.getLogger(StoreSQL.class);
    private final Config config;
    private Connection connect;

    public StoreSQL(Config config) {
        this.config = config;
        try {
            connect = DriverManager.getConnection(this.config.get("url"));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Successfully create "size" of new entries, old will be removed.
     *
     * @param size quantity
     */
    public void generate(int size) {
        this.checkTablePresence();
        this.removeOldData();
        this.generateData(size);
    }

    /**
     * Generates "size" of new entries.
     *
     * @param size quantity
     */
    private void generateData(int size) {
        try {
            this.connect.setAutoCommit(false);
            for (int i = 0; i < size; i++) {
                PreparedStatement st = this.connect.prepareStatement("insert into entry(field) values(?);");
                st.setInt(1, i);
                st.executeUpdate();
                st.close();
            }
            this.connect.commit();
            this.connect.setAutoCommit(true);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            try {
                this.connect.rollback();
            } catch (SQLException e1) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    /**
     * Clear old data.
     */
    private void removeOldData() {
        try (Statement st = this.connect.createStatement()) {
            st.executeUpdate("delete from entry;");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Creates table if it's absent
     */
    private void checkTablePresence() {
        try (Statement st = this.connect.createStatement()) {
            var sql = "CREATE TABLE IF NOT EXISTS entry (\n"
                    + "    field integer \n"
                    + ");";
            st.execute(sql);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Returns all entries from DB
     *
     * @return list of entries
     */
    public List<Entry> load() {
        List<Entry> result = new ArrayList<>();
        try (Statement st = this.connect.createStatement();
             ResultSet set = st.executeQuery("select * from entry;")) {
            while (set.next()) {
                result.add(new Entry(set.getInt("field")));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        if (connect != null) {
            connect.close();
        }
    }

    public static void main(String[] args) {
        new StoreSQL(new Config())
                .generate(3);
    }
}
