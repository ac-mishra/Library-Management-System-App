package com.librarymanagement.ui.dashboard;

import com.librarymanagement.entity.User;
import com.librarymanagement.ui.LoginFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Dashboard Screen.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public class DashboardFrame extends JFrame {

    private final User loggedInUser;

    public DashboardFrame(User user) {

        this.loggedInUser = user;

        initializeUI();

    }

    private void initializeUI() {

        setTitle("Library Management System - Dashboard");

        setSize(900, 600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JLabel title = new JLabel(
                "LIBRARY MANAGEMENT SYSTEM",
                SwingConstants.CENTER
        );

        title.setFont(new Font("Segoe UI", Font.BOLD, 26));

        add(title, BorderLayout.NORTH);

        JLabel welcome = new JLabel(
                "Welcome, " + loggedInUser.getFullName(),
                SwingConstants.CENTER
        );

        welcome.setFont(new Font("Segoe UI", Font.PLAIN, 20));

        add(welcome, BorderLayout.CENTER);

        JPanel panel = new JPanel(new FlowLayout());

        JButton booksButton = new JButton("Books");

        JButton membersButton = new JButton("Members");

        JButton borrowButton = new JButton("Borrow / Return");

        JButton reportsButton = new JButton("Reports");

        JButton logoutButton = new JButton("Logout");

        panel.add(booksButton);

        panel.add(membersButton);

        panel.add(borrowButton);

        panel.add(reportsButton);

        panel.add(logoutButton);

        add(panel, BorderLayout.SOUTH);

        logoutButton.addActionListener(e -> {

            dispose();

            new LoginFrame();

        });

        setVisible(true);

    }

}