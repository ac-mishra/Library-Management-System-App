package com.librarymanagement;

import com.formdev.flatlaf.FlatLightLaf;
import com.librarymanagement.config.DBConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Entry point of the Library Management System.
 *
 * Initializes the application, configures the look and feel,
 * verifies the database connection, and prepares the application
 * for launching the user interface.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public final class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    private Main() {
        // Prevent instantiation
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            try {

                // Apply FlatLaf theme
                UIManager.setLookAndFeel(new FlatLightLaf());
                LOGGER.info("FlatLaf initialized successfully.");

                // Test database connection
                try (Connection connection = DBConnection.getConnection()) {

                    LOGGER.info("Database connection successful.");

                    JOptionPane.showMessageDialog(
                            null,
                            "Library Management System started successfully!\n\nDatabase Connected.",
                            "Application Started",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    // TODO:
                    // Launch LoginFrame in Sprint 2

                }

            } catch (SQLException exception) {

                LOGGER.error("Database connection failed.", exception);

                JOptionPane.showMessageDialog(
                        null,
                        "Unable to connect to the database.\n\n"
                                + exception.getMessage(),
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE
                );

            } catch (Exception exception) {

                LOGGER.error("Application startup failed.", exception);

                JOptionPane.showMessageDialog(
                        null,
                        "Unexpected application error.\n\n"
                                + exception.getMessage(),
                        "Application Error",
                        JOptionPane.ERROR_MESSAGE
                );

            }

        });

    }
}