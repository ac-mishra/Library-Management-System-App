package com.librarymanagement.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

/**
 * Utility class responsible for creating database connections.
 *
 * Reads configuration from application.properties.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public final class DBConnection {

    private static final Logger LOGGER = LogManager.getLogger(DBConnection.class);

    private static final String PROPERTIES_FILE = "application.properties";

    private static final Properties PROPERTIES = new Properties();

    static {

        try (InputStream inputStream =
                     DBConnection.class.getClassLoader()
                             .getResourceAsStream(PROPERTIES_FILE)) {

            Objects.requireNonNull(inputStream,
                    "application.properties not found.");

            PROPERTIES.load(inputStream);

            Class.forName(PROPERTIES.getProperty("db.driver"));

            LOGGER.info("Database driver loaded successfully.");

        } catch (IOException e) {

            LOGGER.error("Unable to load application.properties", e);

            throw new ExceptionInInitializerError(e);

        } catch (ClassNotFoundException e) {

            LOGGER.error("MySQL JDBC Driver not found.", e);

            throw new ExceptionInInitializerError(e);

        }

    }

    /**
     * Private constructor.
     */
    private DBConnection() {
    }

    /**
     * Returns a new database connection.
     *
     * @return JDBC Connection
     * @throws SQLException if connection fails
     */
    public static Connection getConnection() throws SQLException {

        Connection connection = DriverManager.getConnection(
                PROPERTIES.getProperty("db.url"),
                PROPERTIES.getProperty("db.username"),
                PROPERTIES.getProperty("db.password")
        );

        LOGGER.info("Database connection established.");

        return connection;

    }

}