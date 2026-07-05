package com.librarymanagement.ui.dashboard;

import com.librarymanagement.entity.User;
import com.librarymanagement.ui.LoginFrame;
import com.librarymanagement.ui.books.BookManagementFrame;
import com.librarymanagement.ui.books.ReturnBookFrame;
import com.librarymanagement.ui.issue.IssueBookFrame;
import com.librarymanagement.ui.members.MemberManagementFrame;
import com.librarymanagement.ui.reports.ReportsDashboardFrame;
import com.librarymanagement.ui.theme.DashboardCard;
import com.librarymanagement.ui.theme.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Professional Dashboard Screen.
 *
 * @author Amrit Chandan Mishra
 * @version 2.0.0
 */
public class DashboardFrame extends JFrame {

    private final User loggedInUser;

    private DashboardCard booksCard;

    private DashboardCard membersCard;

    private DashboardCard issueCard;

    private DashboardCard returnCard;

    private DashboardCard reportsCard;

    public DashboardFrame(User user) {

        this.loggedInUser = user;

        initializeUI();

        registerEvents();

        setVisible(true);

    }

    private void initializeUI() {

        setTitle("Library Management System");

        setSize(1400, 800);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel root = new JPanel(new BorderLayout());

        root.setBackground(Theme.BACKGROUND);

        root.setBorder(new EmptyBorder(20, 20, 20, 20));

        //---------------- HEADER ----------------//

        JPanel header = new JPanel(new BorderLayout());

        header.setBackground(Theme.PRIMARY);

        header.setBorder(new EmptyBorder(20, 25, 20, 25));

        JLabel title = new JLabel("📚 LIBRARY MANAGEMENT SYSTEM");

        title.setForeground(Color.WHITE);

        title.setFont(new Font(
                "Segoe UI",
                Font.BOLD,
                28
        ));

        header.add(title, BorderLayout.WEST);

        JLabel welcome = new JLabel(

                "<html>Welcome <b>"

                        + loggedInUser.getFullName()

                        + "</b><br>"

                        + LocalDate.now().format(

                        DateTimeFormatter.ofPattern(
                                "dd MMM yyyy"
                        )

                )

                        + "</html>"

        );

        welcome.setForeground(Color.WHITE);

        welcome.setFont(new Font(
                "Segoe UI",
                Font.PLAIN,
                16
        ));

        header.add(
                welcome,
                BorderLayout.EAST
        );

        root.add(
                header,
                BorderLayout.NORTH
        );

        //---------------- DASHBOARD CARDS ----------------//

        JPanel centerPanel = new JPanel(
                new GridLayout(
                        2,
                        3,
                        25,
                        25
                )
        );

        centerPanel.setBorder(
                new EmptyBorder(
                        30,
                        10,
                        30,
                        10
                )
        );

        centerPanel.setBackground(
                Theme.BACKGROUND
        );

        booksCard = new DashboardCard(
                "📚",
                "Books",
                "Manage Books\nAdd • Update • Delete",
                Theme.PRIMARY
        );

        membersCard = new DashboardCard(
                "👥",
                "Members",
                "Manage Library Members",
                Theme.SUCCESS
        );

        issueCard = new DashboardCard(
                "📖",
                "Issue Book",
                "Issue Books To Members",
                Theme.WARNING
        );

        returnCard = new DashboardCard(
                "↩",
                "Return Book",
                "Return Books & Calculate Fine",
                Theme.DANGER
        );

        reportsCard = new DashboardCard(
                "📊",
                "Reports",
                "Generate Reports",
                new Color(123, 31, 162)
        );

        centerPanel.add(booksCard);

        centerPanel.add(membersCard);

        centerPanel.add(issueCard);

        centerPanel.add(returnCard);

        centerPanel.add(reportsCard);

        root.add(
                centerPanel,
                BorderLayout.CENTER);

//---------------- FOOTER ----------------//

        JPanel footer = new JPanel(
                new BorderLayout()
        );

        footer.setBackground(
                Theme.PRIMARY
        );

        footer.setBorder(
                new EmptyBorder(
                        10,
                        20,
                        10,
                        20
                )
        );

        JLabel footerLabel = new JLabel(

                "© 2026 Library Management System | Developed by Amrit Chandan Mishra"

        );

        footerLabel.setForeground(
                Color.WHITE
        );

        footerLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        footer.add(
                footerLabel,
                BorderLayout.WEST
        );

        JButton logoutButton =
                Theme.createButton(
                        "Logout",
                        Theme.DANGER
                );

        footer.add(
                logoutButton,
                BorderLayout.EAST
        );

        root.add(
                footer,
                BorderLayout.SOUTH
        );

        setContentPane(root);
        logoutButton.putClientProperty("logout", true);
    }
// Store logout button in root pane

    private void registerEvents() {

        booksCard.getButton().addActionListener(e -> {

            new BookManagementFrame();

        });

        membersCard.getButton().addActionListener(e -> {

            new MemberManagementFrame();

        });

        issueCard.getButton().addActionListener(e -> {

            new IssueBookFrame(loggedInUser);

        });

        returnCard.getButton().addActionListener(e -> {

            new ReturnBookFrame();

        });

        reportsCard.getButton().addActionListener(e -> {

            new ReportsDashboardFrame();

        });

        JButton logoutButton = findLogoutButton(getContentPane());

        if (logoutButton != null) {

            logoutButton.addActionListener(e -> {

                dispose();

                new LoginFrame();

            });

        }
    }
        private JButton findLogoutButton(Container container) {

            for (Component component : container.getComponents()) {

                if (component instanceof JButton button) {

                    Object property = button.getClientProperty("logout");

                    if (Boolean.TRUE.equals(property)) {

                        return button;

                    }

                }

                if (component instanceof Container child) {

                    JButton button = findLogoutButton(child);

                    if (button != null) {

                        return button;

                    }

                }

            }

            return null;

        }

    }

