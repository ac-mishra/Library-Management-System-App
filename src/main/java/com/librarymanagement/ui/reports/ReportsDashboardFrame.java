package com.librarymanagement.ui.reports;

import com.librarymanagement.entity.BookIssue;
import com.librarymanagement.service.BookIssueService;
import com.librarymanagement.service.BookService;
import com.librarymanagement.service.MemberService;
import com.librarymanagement.service.impl.BookIssueServiceImpl;
import com.librarymanagement.service.impl.BookServiceImpl;
import com.librarymanagement.service.impl.MemberServiceImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ReportsDashboardFrame extends JFrame {

    /*====================================================
                        COLORS
    ====================================================*/

    private static final Color PRIMARY =
            new Color(25,118,210);

    private static final Color SUCCESS =
            new Color(46,125,50);

    private static final Color WARNING =
            new Color(251,140,0);

    private static final Color DANGER =
            new Color(211,47,47);

    private static final Color PURPLE =
            new Color(123,31,162);

    private static final Color LIGHT =
            new Color(245,247,250);

    private static final Font TITLE_FONT =
            new Font("Segoe UI",Font.BOLD,30);

    private static final Font CARD_TITLE =
            new Font("Segoe UI",Font.BOLD,15);

    private static final Font CARD_VALUE =
            new Font("Segoe UI",Font.BOLD,28);

    /*====================================================
                        SERVICES
    ====================================================*/

    private final BookService bookService =
            new BookServiceImpl();

    private final MemberService memberService =
            new MemberServiceImpl();

    private final BookIssueService issueService =
            new BookIssueServiceImpl();

    /*====================================================
                        COMPONENTS
    ====================================================*/

    private JPanel rootPanel;

    private JPanel cardPanel;

    private JPanel chartPanel;

    private JPanel buttonPanel;

    private JLabel lblBooks;

    private JLabel lblMembers;

    private JLabel lblIssued;

    private JLabel lblReturned;

    private JLabel lblOverdue;

    private JLabel lblFine;

    private JButton btnRefresh =
            new JButton("🔄 Refresh");

    private JButton btnPDF =
            new JButton("📄 Export PDF");

    private JButton btnExcel =
            new JButton("📊 Export Excel");

    private JButton btnPrint =
            new JButton("🖨 Print");

    /*====================================================
                        CONSTRUCTOR
    ====================================================*/

    public ReportsDashboardFrame(){

        initializeUI();

        loadStatistics();

        setVisible(true);

    }

    /*====================================================
                        UI
    ====================================================*/

    private void initializeUI(){

        setTitle("Reports Dashboard");

        setSize(1600,900);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        rootPanel =
                new JPanel(
                        new BorderLayout(
                                20,
                                20
                        )
                );

        rootPanel.setBackground(LIGHT);

        rootPanel.setBorder(
                new EmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );

        createHeader();

        createCards();

        createCharts();

        createButtons();

        add(rootPanel);

    }

    private void createHeader(){

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setBackground(PRIMARY);

        header.setBorder(
                new EmptyBorder(
                        20,
                        25,
                        20,
                        25
                )
        );

        JLabel title =
                new JLabel(
                        "📊 REPORTS DASHBOARD"
                );

        title.setFont(TITLE_FONT);

        title.setForeground(Color.WHITE);

        JLabel date =
                new JLabel(
                        java.time.LocalDate.now().toString()
                );

        date.setForeground(Color.WHITE);

        date.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );

        header.add(
                title,
                BorderLayout.WEST
        );

        header.add(
                date,
                BorderLayout.EAST
        );

        rootPanel.add(
                header,
                BorderLayout.NORTH
        );

    }
    private void createCards() {

        cardPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                3,
                                20,
                                20
                        )
                );

        cardPanel.setOpaque(false);

        lblBooks =
                new JLabel(
                        "0",
                        SwingConstants.CENTER
                );

        lblMembers =
                new JLabel(
                        "0",
                        SwingConstants.CENTER
                );

        lblIssued =
                new JLabel(
                        "0",
                        SwingConstants.CENTER
                );

        lblReturned =
                new JLabel(
                        "0",
                        SwingConstants.CENTER
                );

        lblOverdue =
                new JLabel(
                        "0",
                        SwingConstants.CENTER
                );

        lblFine =
                new JLabel(
                        "₹ 0.00",
                        SwingConstants.CENTER
                );

        cardPanel.add(
                createCard(
                        "📚 Total Books",
                        lblBooks,
                        PRIMARY
                )
        );

        cardPanel.add(
                createCard(
                        "👤 Total Members",
                        lblMembers,
                        SUCCESS
                )
        );

        cardPanel.add(
                createCard(
                        "📖 Issued Books",
                        lblIssued,
                        WARNING
                )
        );

        cardPanel.add(
                createCard(
                        "↩ Returned Books",
                        lblReturned,
                        new Color(0,150,136)
                )
        );

        cardPanel.add(
                createCard(
                        "⚠ Overdue Books",
                        lblOverdue,
                        DANGER
                )
        );

        cardPanel.add(
                createCard(
                        "💰 Total Fine",
                        lblFine,
                        PURPLE
                )
        );

        rootPanel.add(
                cardPanel,
                BorderLayout.CENTER
        );

    }

    private JPanel createCard(
            String title,
            JLabel valueLabel,
            Color color){

        JPanel card =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        card.setBackground(Color.WHITE);

        card.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                new Color(
                                        225,
                                        225,
                                        225
                                ),
                                1,
                                true
                        ),

                        new EmptyBorder(
                                20,
                                20,
                                20,
                                20
                        )

                )
        );

        JPanel top =
                new JPanel(
                        new BorderLayout()
                );

        top.setOpaque(false);

        JLabel lblTitle =
                new JLabel(title);

        lblTitle.setFont(CARD_TITLE);

        lblTitle.setForeground(
                new Color(
                        80,
                        80,
                        80
                )
        );

        JPanel dot =
                new JPanel();

        dot.setBackground(color);

        dot.setPreferredSize(
                new Dimension(
                        18,
                        18
                )
        );

        top.add(
                lblTitle,
                BorderLayout.WEST
        );

        top.add(
                dot,
                BorderLayout.EAST
        );

        valueLabel.setFont(CARD_VALUE);

        valueLabel.setForeground(color);

        card.add(
                top,
                BorderLayout.NORTH
        );

        card.add(
                valueLabel,
                BorderLayout.CENTER
        );

        return card;

    }
    private void createCharts() {

        chartPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                20,
                                20
                        )
                );

        chartPanel.setOpaque(false);

        JPanel monthlyChart =
                createChartCard(
                        "📈 Monthly Library Statistics"
                );

        JPanel categoryChart =
                createChartCard(
                        "📊 Library Summary"
                );

        chartPanel.add(monthlyChart);

        chartPanel.add(categoryChart);

        JPanel container =
                new JPanel(
                        new BorderLayout(
                                20,
                                20
                        )
                );

        container.setOpaque(false);

        container.add(
                cardPanel,
                BorderLayout.NORTH
        );

        container.add(
                chartPanel,
                BorderLayout.CENTER
        );

        rootPanel.add(
                container,
                BorderLayout.CENTER
        );

    }

    private JPanel createChartCard(String title){

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(Color.WHITE);

        panel.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                new Color(
                                        225,
                                        225,
                                        225
                                ),
                                1,
                                true
                        ),

                        new EmptyBorder(
                                20,
                                20,
                                20,
                                20
                        )

                )
        );

        JLabel lblTitle =
                new JLabel(title);

        lblTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        panel.add(
                lblTitle,
                BorderLayout.NORTH
        );

        JPanel chartArea =
                new JPanel(){

                    @Override
                    protected void paintComponent(Graphics g){

                        super.paintComponent(g);

                        Graphics2D g2 =
                                (Graphics2D) g;

                        g2.setRenderingHint(
                                RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON
                        );

                        int w = getWidth();

                        int h = getHeight();

                        int base = h - 40;

                        int[] values = {
                                120,
                                180,
                                140,
                                220,
                                260,
                                200
                        };

                        Color[] colors = {

                                PRIMARY,

                                SUCCESS,

                                WARNING,

                                DANGER,

                                PURPLE,

                                new Color(
                                        0,
                                        150,
                                        136
                                )

                        };

                        int x = 50;

                        for(int i=0;i<values.length;i++){

                            int barHeight =
                                    values[i];

                            g2.setColor(
                                    colors[i]
                            );

                            g2.fillRoundRect(

                                    x,

                                    base-barHeight,

                                    45,

                                    barHeight,

                                    12,

                                    12

                            );

                            x += 70;

                        }

                    }

                };

        chartArea.setBackground(Color.WHITE);

        panel.add(
                chartArea,
                BorderLayout.CENTER
        );

        return panel;

    }

    private void createButtons(){

        buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                15,
                                10
                        )
                );

        buttonPanel.setOpaque(false);

        styleActionButton(btnRefresh,PRIMARY);

        styleActionButton(btnPDF,DANGER);

        styleActionButton(btnExcel,SUCCESS);

        styleActionButton(btnPrint,WARNING);

        buttonPanel.add(btnRefresh);

        buttonPanel.add(btnPDF);

        buttonPanel.add(btnExcel);

        buttonPanel.add(btnPrint);

        rootPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        btnRefresh.addActionListener(e->loadStatistics());

        btnPDF.addActionListener(e->exportPDF());

        btnExcel.addActionListener(e->exportExcel());

        btnPrint.addActionListener(e->printReport());

    }

    private void styleActionButton(
            JButton button,
            Color color){

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        button.setBackground(color);

        button.setForeground(Color.WHITE);

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setCursor(
                Cursor.getPredefinedCursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setPreferredSize(
                new Dimension(
                        180,
                        42
                )
        );

    }
    private void loadStatistics() {

        try {

            lblBooks.setText(
                    String.valueOf(
                            bookService.findAll().size()
                    )
            );

            lblMembers.setText(
                    String.valueOf(
                            memberService.findAll().size()
                    )
            );

            int issued = 0;

            int returned = 0;

            int overdue = 0;

            double totalFine = 0.0;

            for (BookIssue issue : issueService.findAll()) {

                if ("ISSUED".equalsIgnoreCase(issue.getStatus())) {

                    issued++;

                    if (issue.getDueDate() != null &&
                            issue.getDueDate().isBefore(java.time.LocalDate.now())) {

                        overdue++;

                    }

                }

                if ("RETURNED".equalsIgnoreCase(issue.getStatus())) {

                    returned++;

                }

                if (issue.getFineAmount() != null) {

                    totalFine += issue.getFineAmount();

                }

            }

            lblIssued.setText(
                    String.valueOf(issued)
            );

            lblReturned.setText(
                    String.valueOf(returned)
            );

            lblOverdue.setText(
                    String.valueOf(overdue)
            );

            lblFine.setText(
                    "₹ " + String.format("%.2f", totalFine)
            );

        }

        catch (Exception exception) {

            JOptionPane.showMessageDialog(

                    this,

                    exception.getMessage(),

                    "Statistics Error",

                    JOptionPane.ERROR_MESSAGE

            );

        }

    }

    private int getTotalBooks() {

        return bookService.findAll().size();

    }

    private int getTotalMembers() {

        return memberService.findAll().size();

    }

    private int getIssuedBooks() {

        int count = 0;

        for (BookIssue issue : issueService.findAll()) {

            if ("ISSUED".equalsIgnoreCase(issue.getStatus())) {

                count++;

            }

        }

        return count;

    }

    private int getReturnedBooks() {

        int count = 0;

        for (BookIssue issue : issueService.findAll()) {

            if ("RETURNED".equalsIgnoreCase(issue.getStatus())) {

                count++;

            }

        }

        return count;

    }

    private int getOverdueBooks() {

        int count = 0;

        java.time.LocalDate today =
                java.time.LocalDate.now();

        for (BookIssue issue : issueService.findAll()) {

            if ("ISSUED".equalsIgnoreCase(issue.getStatus())
                    && issue.getDueDate() != null
                    && issue.getDueDate().isBefore(today)) {

                count++;

            }

        }

        return count;

    }

    private double getTotalFine() {

        double total = 0.0;

        for (BookIssue issue : issueService.findAll()) {

            if (issue.getFineAmount() != null) {

                total += issue.getFineAmount();

            }

        }

        return total;

    }
    private void exportPDF() {

        JOptionPane.showMessageDialog(
                this,
                "PDF Export feature will be available in the next version."
        );

    }

    private void exportExcel() {

        JOptionPane.showMessageDialog(
                this,
                "Excel Export feature will be available in the next version."
        );

    }

    private void printReport() {

        try {

            JTextArea report =
                    new JTextArea();

            report.append(
                    "========== LIBRARY REPORT ==========\n\n"
            );

            report.append(
                    "Total Books      : "
                            + getTotalBooks()
                            + "\n"
            );

            report.append(
                    "Total Members    : "
                            + getTotalMembers()
                            + "\n"
            );

            report.append(
                    "Books Issued     : "
                            + getIssuedBooks()
                            + "\n"
            );

            report.append(
                    "Books Returned   : "
                            + getReturnedBooks()
                            + "\n"
            );

            report.append(
                    "Overdue Books    : "
                            + getOverdueBooks()
                            + "\n"
            );

            report.append(
                    "Total Fine       : ₹"
                            + String.format(
                            "%.2f",
                            getTotalFine()
                    )
                            + "\n"
            );

            report.append(
                    "\n===================================="
            );

            boolean printed =
                    report.print();

            if (printed) {

                JOptionPane.showMessageDialog(
                        this,
                        "Report printed successfully."
                );

            }

        }

        catch (Exception exception) {

            JOptionPane.showMessageDialog(

                    this,

                    exception.getMessage(),

                    "Print Error",

                    JOptionPane.ERROR_MESSAGE

            );

        }

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            try {

                UIManager.setLookAndFeel(

                        UIManager.getSystemLookAndFeelClassName()

                );

            } catch (Exception ignored) {
            }

            new ReportsDashboardFrame();

        });

    }
}