package co.inventorsoft.academy.jdbc.connection;

import co.inventorsoft.academy.jdbc.exception.PropertiesException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    private static final String APP_SETTINGS_FILE = "application.properties";

    private ConnectionManager() { }

    public static Connection getConnection() {
        try {
            Properties props = getProperties();
            String url = props.getProperty("url");

            return DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            //log
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (PropertiesException e) {
            //log
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static Properties getProperties() throws PropertiesException {
        try (InputStream propFile = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(APP_SETTINGS_FILE)) {
            Properties appProperties = new Properties();
            appProperties.load(propFile);

            return appProperties;
        } catch (IOException e) {
            //log
            System.out.println("Error! Application properties Exception: Can't read app.properties from file! " + e.getMessage());
            throw new PropertiesException(e.getMessage());
        }
    }

}