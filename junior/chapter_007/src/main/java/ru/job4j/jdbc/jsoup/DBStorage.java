package ru.job4j.jdbc.jsoup;

import ru.job4j.jdbc.tracker.ConnectionFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TODO Description
 * Created by roman.pogorelov on 25.09.2019
 */
public class DBStorage {
    private static final Logger LOG = Logger.getLogger(DBStorage.class.getName());
    private String propertiesFileName = "app.properties";
    private java.sql.Connection connection = ConnectionFactory.getConnection(this.propertiesFileName);

    public DBStorage() {
    }

    public DBStorage(String propertiesFileName) {
        this.propertiesFileName = propertiesFileName;
        this.connection = ConnectionFactory.getConnection(this.propertiesFileName);
    }

    /**
     * Saving all vacancies in DB.
     */
    public void saveInDB(Map<String, Vacancy> vacancies) {
        try (PreparedStatement st = connection.prepareStatement("insert into vacancy(name, description, link) values(?,?,?)")
        ) {
            connection.setAutoCommit(false);
            for (Map.Entry<String, Vacancy> entry : vacancies.entrySet()) {
                st.setString(1, entry.getValue().getName());
                st.setString(2, entry.getValue().getDescription());
                st.setString(3, entry.getValue().getLink());
                st.addBatch();
                st.clearParameters();
            }
            st.executeBatch();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            LOG.log(Level.ALL, e.getMessage(), e);
            try {
                connection.rollback();
            } catch (SQLException e1) {
                LOG.log(Level.ALL, e.getMessage(), e);
            }
        }
    }

    /**
     * Check vacancy table in DB, whether it's empty or not.
     *
     * @return whether vacancy table is empty or not
     */
    public boolean checkIsFirstRun() {
        boolean result = false;
        ResultSet resultSet;
        try (java.sql.Connection connection = ConnectionFactory.getConnection(propertiesFileName)) {
            Statement st = connection.createStatement();
            resultSet = st.executeQuery("select id from vacancy limit 1;");
            result = resultSet != null && !resultSet.next();
        } catch (SQLException e) {
            LOG.log(Level.ALL, e.getMessage(), e);
        }
        return result;
    }

}
