package ru.job4j.jdbc.tracker;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by roman.pogorelov on 15.09.2019
 */
public class ConnectionFactory {

    public static Connection getConnection(String propertiesFileName) {
        Connection result = null;
        Properties properties = new Properties();
        try (InputStream in = ConnectionFactory.class.getClassLoader().getResourceAsStream(propertiesFileName)) {
            properties.load(in);
            Class.forName(properties.getProperty("driver-class-name"));
            result = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password")
            );
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
