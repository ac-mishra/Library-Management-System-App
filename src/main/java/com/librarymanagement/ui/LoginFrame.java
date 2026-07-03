package com.librarymanagement.ui;

import com.librarymanagement.dao.impl.UserDAOImpl;
import com.librarymanagement.entity.User;
import com.librarymanagement.service.AuthService;
import com.librarymanagement.service.impl.AuthServiceImpl;
import com.librarymanagement.ui.dashboard.DashboardFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Optional;

/**
 * Login Screen for Library Management System.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public class LoginFrame extends JFrame {

    private final JTextField txtUsername;
    private final JPasswordField txtPassword;
    private final JCheckBox chkShowPassword;
    private final JButton btnLogin;
    private final JButton btnExit;

    private final AuthService authService;

    public LoginFrame() {

        authService = new AuthServiceImpl(new UserDAOImpl());

        setTitle("Library Management System");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new EmptyBorder(30, 30, 30, 30));
        panel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitle = new JLabel("LIBRARY MANAGEMENT SYSTEM");

        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));

        lblTitle.setForeground(new Color(44, 62, 80));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        panel.add(lblTitle, gbc);

        JLabel lblWelcome =
                new JLabel("Welcome Back!");

        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);

        lblWelcome.setFont(
                new Font("Segoe UI", Font.PLAIN, 18)
        );

        gbc.gridy++;

        panel.add(lblWelcome, gbc);

        gbc.gridwidth = 1;

        JLabel lblUsername =
                new JLabel("Username");

        lblUsername.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        gbc.gridy++;
        gbc.gridx = 0;

        panel.add(lblUsername, gbc);

        txtUsername =
                new JTextField(20);

        txtUsername.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        gbc.gridx = 1;

        panel.add(txtUsername, gbc);

        JLabel lblPassword =
                new JLabel("Password");

        lblPassword.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        gbc.gridy++;
        gbc.gridx = 0;

        panel.add(lblPassword, gbc);

        txtPassword =
                new JPasswordField(20);

        txtPassword.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        gbc.gridx = 1;

        panel.add(txtPassword, gbc);
        chkShowPassword = new JCheckBox("Show Password");

        chkShowPassword.setBackground(Color.WHITE);

        chkShowPassword.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );

        gbc.gridy++;
        gbc.gridx = 1;

        panel.add(chkShowPassword, gbc);

        btnLogin = new JButton("LOGIN");

        btnLogin.setBackground(new Color(41, 128, 185));
        btnLogin.setForeground(Color.WHITE);

        btnLogin.setFocusPainted(false);

        btnLogin.setFont(
                new Font("Segoe UI", Font.BOLD, 15)
        );

        gbc.gridy++;
        gbc.gridx = 0;

        panel.add(btnLogin, gbc);

        btnExit = new JButton("EXIT");

        btnExit.setBackground(new Color(231, 76, 60));
        btnExit.setForeground(Color.WHITE);

        btnExit.setFocusPainted(false);

        btnExit.setFont(
                new Font("Segoe UI", Font.BOLD, 15)
        );

        gbc.gridx = 1;

        panel.add(btnExit, gbc);

        JLabel lblFooter =
                new JLabel("© 2026 Amrit Chandan Mishra");

        lblFooter.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        lblFooter.setForeground(Color.GRAY);

        lblFooter.setFont(
                new Font("Segoe UI", Font.PLAIN, 12)
        );

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;

        panel.add(lblFooter, gbc);

        add(panel);

        registerEvents();

        setVisible(true);
    }

    /**
     * Register all button and checkbox events.
     */
    private void registerEvents() {

        chkShowPassword.addActionListener(e -> {

            if (chkShowPassword.isSelected()) {

                txtPassword.setEchoChar((char) 0);

            } else {

                txtPassword.setEchoChar('•');

            }

        });

        btnExit.addActionListener(e -> System.exit(0));

        btnLogin.addActionListener(this::login);

        getRootPane().setDefaultButton(btnLogin);

    }

    /**
     * Handles user login.
     *
     * @param event action event
     */
    private void login(ActionEvent event) {

        String username = txtUsername.getText().trim();

        String password = new String(
                txtPassword.getPassword()
        );

        if (username.isBlank()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter username.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE
            );

            txtUsername.requestFocus();

            return;

        }

        if (password.isBlank()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter password.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE
            );

            txtPassword.requestFocus();

            return;

        }

        btnLogin.setEnabled(false);

        setCursor(
                Cursor.getPredefinedCursor(
                        Cursor.WAIT_CURSOR
                )
        );

        try {

            Optional<User> optionalUser =
                    authService.login(
                            username,
                            password
                    );

            if (optionalUser.isPresent()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Login Successful!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                SwingUtilities.invokeLater(() -> {

                    new DashboardFrame(
                            optionalUser.get()
                    );

                });

                dispose();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid username or password.",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE
                );

                txtPassword.setText("");

                txtPassword.requestFocus();

            }

        } catch (Exception exception) {

            JOptionPane.showMessageDialog(
                    this,
                    exception.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            exception.printStackTrace();

        } finally {

            btnLogin.setEnabled(true);

            setCursor(
                    Cursor.getDefaultCursor()
            );
        }
    }

            /**
             * Application Entry Point.
             *
             * @param args command line arguments
             */
            public static void main (String[]args){

                SwingUtilities.invokeLater(() -> {

                    try {

                        UIManager.setLookAndFeel(
                                UIManager.getSystemLookAndFeelClassName()
                        );

                    } catch (Exception ignored) {
                    }

                    new LoginFrame();

                });

            }

        }

