package com.librarymanagement.ui.books;

import com.librarymanagement.entity.BookIssue;
import com.librarymanagement.service.BookIssueService;
import com.librarymanagement.service.impl.BookIssueServiceImpl;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.ZoneId;
import java.util.Optional;

public class ReturnBookFrame extends JFrame {

    /*====================================================
                        COLORS
    ====================================================*/

    private static final Color PRIMARY =
            new Color(25,118,210);

    private static final Color SUCCESS =
            new Color(46,125,50);

    private static final Color DANGER =
            new Color(211,47,47);

    private static final Color LIGHT =
            new Color(245,247,250);

    private static final Color BORDER =
            new Color(220,220,220);

    private static final Font TITLE_FONT =
            new Font("Segoe UI",Font.BOLD,28);

    private static final Font LABEL_FONT =
            new Font("Segoe UI",Font.BOLD,14);

    private static final Font FIELD_FONT =
            new Font("Segoe UI",Font.PLAIN,14);

    /*====================================================
                        COMPONENTS
    ====================================================*/

    private JPanel rootPanel;

    private JPanel centerPanel;

    private JPanel leftPanel;

    private JPanel rightPanel;

    private JPanel searchPanel;

    private JPanel buttonPanel;

    private JComboBox<BookIssue> cmbIssue =
            new JComboBox<>();

    private JTextField txtBook =
            new JTextField();

    private JTextField txtMember =
            new JTextField();

    private JTextField txtIssueDate =
            new JTextField();

    private JTextField txtDueDate =
            new JTextField();

    private JDateChooser dcReturnDate =
            new JDateChooser();

    private JTextField txtFine =
            new JTextField();

    private JComboBox<String> cmbCondition =
            new JComboBox<>(new String[]{
                    "GOOD",
                    "DAMAGED",
                    "LOST"
            });

    private JTextField txtSearch =
            new JTextField();

    private JButton btnReturn =
            new JButton("↩ Return");

    private JButton btnUpdate =
            new JButton("✏ Update");

    private JButton btnDelete =
            new JButton("🗑 Delete");

    private JButton btnClear =
            new JButton("🧹 Clear");

    private JButton btnSearch =
            new JButton("🔍 Search");

    private JTable table;

    private DefaultTableModel tableModel;

    /*====================================================
                        SERVICE
    ====================================================*/

    private final BookIssueService issueService =
            new BookIssueServiceImpl();

    /*====================================================
                        CONSTRUCTOR
    ====================================================*/

    public ReturnBookFrame() {

        initializeUI();

        initializeTable();

        registerEvents();

        loadIssuedBooks();

        loadReturnedBooks();

        setVisible(true);

    }

    /*====================================================
                        UI
    ====================================================*/

    private void initializeUI() {

        setTitle("Return Book");

        setSize(1500,900);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        rootPanel =
                new JPanel(
                        new BorderLayout(
                                15,
                                15
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

        createCenterLayout();

        add(rootPanel);

    }

    private void createHeader() {

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
                        "↩ RETURN BOOK"
                );

        title.setForeground(Color.WHITE);

        title.setFont(TITLE_FONT);

        JLabel date =
                new JLabel(
                        java.time.LocalDate.now().toString()
                );

        date.setForeground(Color.WHITE);

        date.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
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
    private void createCenterLayout() {

        centerPanel =
                new JPanel(
                        new BorderLayout(
                                20,
                                20
                        )
                );

        centerPanel.setOpaque(false);

        leftPanel =
                createFormPanel();

        rightPanel =
                new JPanel(
                        new BorderLayout(
                                15,
                                15
                        )
                );

        rightPanel.setOpaque(false);

        createSearchPanel();

        rightPanel.add(
                searchPanel,
                BorderLayout.NORTH
        );

        centerPanel.add(
                leftPanel,
                BorderLayout.WEST
        );

        centerPanel.add(
                rightPanel,
                BorderLayout.CENTER
        );

        rootPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

    }

    private JPanel createFormPanel() {

        JPanel panel =
                new JPanel(
                        new GridBagLayout()
                );

        panel.setPreferredSize(
                new Dimension(
                        430,
                        700
                )
        );

        panel.setBackground(Color.WHITE);

        panel.setBorder(
                BorderFactory.createCompoundBorder(

                        new LineBorder(
                                BORDER,
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

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        8,
                        8,
                        8,
                        8
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1;

        int row = 0;

        addField(
                panel,
                gbc,
                row++,
                "Issued Book",
                cmbIssue
        );

        txtBook.setEditable(false);

        addField(
                panel,
                gbc,
                row++,
                "Book",
                txtBook
        );

        txtMember.setEditable(false);

        addField(
                panel,
                gbc,
                row++,
                "Member",
                txtMember
        );

        txtIssueDate.setEditable(false);

        addField(
                panel,
                gbc,
                row++,
                "Issue Date",
                txtIssueDate
        );

        txtDueDate.setEditable(false);

        addField(
                panel,
                gbc,
                row++,
                "Due Date",
                txtDueDate
        );

        dcReturnDate.setDateFormatString(
                "dd-MM-yyyy"
        );

        addField(
                panel,
                gbc,
                row++,
                "Return Date",
                dcReturnDate
        );

        txtFine.setEditable(false);

        txtFine.setText("0.00");

        addField(
                panel,
                gbc,
                row++,
                "Fine",
                txtFine
        );

        addField(
                panel,
                gbc,
                row++,
                "Condition",
                cmbCondition
        );

        createButtonPanel();

        gbc.gridx = 0;

        gbc.gridy = row;

        gbc.gridwidth = 2;

        panel.add(
                buttonPanel,
                gbc
        );

        return panel;

    }

    private void addField(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            String label,
            Component component) {

        JLabel lbl =
                new JLabel(label);

        lbl.setFont(LABEL_FONT);

        gbc.gridx = 0;

        gbc.gridy = row;

        gbc.weightx = 0;

        panel.add(lbl, gbc);

        if(component instanceof JTextField field){

            field.setFont(FIELD_FONT);

            field.setPreferredSize(
                    new Dimension(
                            240,
                            38
                    )
            );

        }

        if(component instanceof JComboBox<?> combo){

            combo.setFont(FIELD_FONT);

            combo.setPreferredSize(
                    new Dimension(
                            240,
                            38
                    )
            );

        }

        gbc.gridx = 1;

        gbc.weightx = 1;

        panel.add(component, gbc);

    }

    private void createSearchPanel() {

        searchPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                15,
                                15
                        )
                );

        searchPanel.setBackground(Color.WHITE);

        searchPanel.setBorder(
                BorderFactory.createCompoundBorder(

                        new LineBorder(
                                BORDER,
                                1,
                                true
                        ),

                        new EmptyBorder(
                                10,
                                10,
                                10,
                                10
                        )

                )
        );

        JLabel lbl =
                new JLabel(
                        "🔍 Search Returned Books"
                );

        lbl.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        txtSearch.setPreferredSize(
                new Dimension(
                        300,
                        40
                )
        );

        searchPanel.add(lbl);

        searchPanel.add(txtSearch);

        searchPanel.add(btnSearch);

        styleButtons();

    }
    private void createButtonPanel() {

        buttonPanel =
                new JPanel(
                        new GridLayout(
                                3,
                                2,
                                12,
                                12
                        )
                );

        buttonPanel.setOpaque(false);

        styleButton(btnReturn,SUCCESS);

        styleButton(btnUpdate,PRIMARY);

        styleButton(btnDelete,DANGER);

        styleButton(btnClear,new Color(97,97,97));

        styleButton(btnSearch,new Color(21,101,192));

        JButton btnRefresh =
                new JButton("🔄 Refresh");

        styleButton(
                btnRefresh,
                new Color(0,150,136)
        );

        btnRefresh.addActionListener(e -> {

            clearForm();

            loadIssuedBooks();

            loadReturnedBooks();

        });

        buttonPanel.add(btnReturn);

        buttonPanel.add(btnUpdate);

        buttonPanel.add(btnDelete);

        buttonPanel.add(btnClear);

        buttonPanel.add(btnSearch);

        buttonPanel.add(btnRefresh);

    }

    private void styleButtons() {

        styleButton(btnReturn,SUCCESS);

        styleButton(btnUpdate,PRIMARY);

        styleButton(btnDelete,DANGER);

        styleButton(btnClear,new Color(97,97,97));

        styleButton(btnSearch,new Color(21,101,192));

    }

    private void styleButton(
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
                        160,
                        42
                )
        );

    }

    private void initializeTable(){

        tableModel =
                new DefaultTableModel(

                        new Object[]{

                                "Issue ID",

                                "Book",

                                "Member",

                                "Issue Date",

                                "Due Date",

                                "Return Date",

                                "Fine",

                                "Status"

                        },

                        0

                ){

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ){

                        return false;

                    }

                };

        table =
                new JTable(tableModel);

        table.setFont(FIELD_FONT);

        table.setRowHeight(36);

        table.setSelectionBackground(
                new Color(
                        220,
                        235,
                        252
                )
        );

        table.setSelectionForeground(
                Color.BLACK
        );

        table.setShowGrid(false);

        table.setAutoCreateRowSorter(true);

        table.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        table.getTableHeader().setBackground(PRIMARY);

        table.getTableHeader().setForeground(Color.WHITE);

        table.getTableHeader().setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        JScrollPane scroll =
                new JScrollPane(table);

        scroll.setBorder(
                BorderFactory.createCompoundBorder(

                        new LineBorder(
                                BORDER,
                                1,
                                true
                        ),

                        new EmptyBorder(
                                10,
                                10,
                                10,
                                10
                        )

                )
        );

        rightPanel.add(
                scroll,
                BorderLayout.CENTER
        );

    }

    private void registerEvents(){

        btnReturn.addActionListener(
                e -> returnBook()
        );

        btnUpdate.addActionListener(
                e -> updateReturn()
        );

        btnDelete.addActionListener(
                e -> deleteReturn()
        );

        btnClear.addActionListener(
                e -> clearForm()
        );

        btnSearch.addActionListener(
                e -> searchReturn()
        );

        txtSearch.addActionListener(
                e -> searchReturn()
        );

        cmbIssue.addActionListener(
                e -> loadIssueDetails()
        );

        dcReturnDate.getDateEditor().addPropertyChangeListener(
                evt -> calculateFine()
        );

        table.getSelectionModel()
                .addListSelectionListener(
                        e -> {

                            if(!e.getValueIsAdjusting()){

                                fillFormFromTable();

                            }

                        }
                );

    }

    private void loadIssuedBooks(){

        cmbIssue.removeAllItems();

        for(BookIssue issue : issueService.findActiveIssues()){

            cmbIssue.addItem(issue);

        }

    }
    private void loadReturnedBooks() {

        tableModel.setRowCount(0);

        for (BookIssue issue : issueService.findAll()) {

            if ("RETURNED".equalsIgnoreCase(issue.getStatus())) {

                tableModel.addRow(new Object[]{

                        issue.getIssueId(),

                        issue.getBook().getTitle(),

                        issue.getMember().getFirstName()
                                + " "
                                + issue.getMember().getLastName(),

                        issue.getIssueDate(),

                        issue.getDueDate(),

                        issue.getReturnDate(),

                        issue.getFineAmount(),

                        issue.getStatus()

                });

            }

        }

    }

    private void loadIssueDetails() {

        if (cmbIssue.getSelectedItem() == null) {

            return;

        }

        BookIssue issue =
                (BookIssue) cmbIssue.getSelectedItem();

        txtBook.setText(
                issue.getBook().getTitle()
        );

        txtMember.setText(
                issue.getMember().getFirstName()
                        + " "
                        + issue.getMember().getLastName()
        );

        txtIssueDate.setText(
                issue.getIssueDate().toString()
        );

        txtDueDate.setText(
                issue.getDueDate().toString()
        );

        dcReturnDate.setDate(
                new java.util.Date()
        );

        calculateFine();

    }

    private void calculateFine() {

        if (cmbIssue.getSelectedItem() == null
                || dcReturnDate.getDate() == null) {

            txtFine.setText("0.00");

            return;

        }

        BookIssue issue =
                (BookIssue) cmbIssue.getSelectedItem();

        java.time.LocalDate returnDate =
                dcReturnDate.getDate()
                        .toInstant()
                        .atZone(
                                ZoneId.systemDefault()
                        )
                        .toLocalDate();

        long lateDays =
                java.time.temporal.ChronoUnit.DAYS.between(
                        issue.getDueDate(),
                        returnDate
                );

        double fine = 0;

        if (lateDays > 0) {

            fine = lateDays * 5.0;

        }

        txtFine.setText(
                String.format(
                        "%.2f",
                        fine
                )
        );

    }

    private void returnBook() {

        try {

            if (cmbIssue.getSelectedItem() == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select an issued book."
                );

                return;

            }

            BookIssue issue =
                    (BookIssue) cmbIssue.getSelectedItem();

            issue.setReturnDate(

                    dcReturnDate.getDate()
                            .toInstant()
                            .atZone(
                                    ZoneId.systemDefault()
                            )
                            .toLocalDate()

            );

            issue.setFineAmount(
                    Double.parseDouble(
                            txtFine.getText()
                    )
            );

            issue.setStatus("RETURNED");

            if (issueService.update(issue)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Book returned successfully."
                );

                clearForm();

                loadIssuedBooks();

                loadReturnedBooks();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Unable to return book."
                );

            }

        } catch (Exception exception) {

            JOptionPane.showMessageDialog(
                    this,
                    exception.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        }

    }
    private void updateReturn() {

        int row = table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a returned record."
            );

            return;

        }

        row = table.convertRowIndexToModel(row);

        Integer issueId = Integer.parseInt(
                tableModel.getValueAt(row,0).toString()
        );

        Optional<BookIssue> optional =
                issueService.findById(issueId);

        if (optional.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Record not found."
            );

            return;

        }

        BookIssue issue = optional.get();

        issue.setReturnDate(
                dcReturnDate.getDate()
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate()
        );

        issue.setFineAmount(
                Double.parseDouble(
                        txtFine.getText()
                )
        );

        issue.setStatus("RETURNED");

        if(issueService.update(issue)){

            JOptionPane.showMessageDialog(
                    this,
                    "Record updated successfully."
            );

            clearForm();

            loadIssuedBooks();

            loadReturnedBooks();

        }else{

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to update record."
            );

        }

    }

    private void deleteReturn() {

        int row = table.getSelectedRow();

        if(row==-1){

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a record."
            );

            return;

        }

        row = table.convertRowIndexToModel(row);

        int option =
                JOptionPane.showConfirmDialog(

                        this,

                        "Delete selected return record?",

                        "Confirm Delete",

                        JOptionPane.YES_NO_OPTION

                );

        if(option!=JOptionPane.YES_OPTION){

            return;

        }

        Integer issueId =
                Integer.parseInt(
                        tableModel.getValueAt(row,0).toString()
                );

        if(issueService.delete(issueId)){

            JOptionPane.showMessageDialog(
                    this,
                    "Record deleted successfully."
            );

            clearForm();

            loadIssuedBooks();

            loadReturnedBooks();

        }else{

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to delete record."
            );

        }

    }

    private void searchReturn() {

        String keyword =
                txtSearch.getText()
                        .trim()
                        .toLowerCase();

        tableModel.setRowCount(0);

        for(BookIssue issue : issueService.findAll()){

            if(!"RETURNED".equalsIgnoreCase(issue.getStatus())){

                continue;

            }

            String book =
                    issue.getBook().getTitle().toLowerCase();

            String member =
                    (issue.getMember().getFirstName()
                            +" "
                            +issue.getMember().getLastName())
                            .toLowerCase();

            if(book.contains(keyword)
                    || member.contains(keyword)){

                tableModel.addRow(new Object[]{

                        issue.getIssueId(),

                        issue.getBook().getTitle(),

                        issue.getMember().getFirstName()
                                +" "
                                +issue.getMember().getLastName(),

                        issue.getIssueDate(),

                        issue.getDueDate(),

                        issue.getReturnDate(),

                        issue.getFineAmount(),

                        issue.getStatus()

                });

            }

        }

    }

    private void fillFormFromTable() {

        int row = table.getSelectedRow();

        if(row==-1){

            return;

        }

        row = table.convertRowIndexToModel(row);

        Integer issueId =
                Integer.parseInt(
                        tableModel.getValueAt(row,0).toString()
                );

        Optional<BookIssue> optional =
                issueService.findById(issueId);

        if(optional.isEmpty()){

            return;

        }

        BookIssue issue = optional.get();

        cmbIssue.setSelectedItem(issue);

        loadIssueDetails();

        if(issue.getReturnDate()!=null){

            dcReturnDate.setDate(
                    java.sql.Date.valueOf(
                            issue.getReturnDate()
                    )
            );

        }

        txtFine.setText(
                String.valueOf(
                        issue.getFineAmount()
                )
        );

    }

    private void clearForm() {

        cmbIssue.setSelectedIndex(-1);

        txtBook.setText("");

        txtMember.setText("");

        txtIssueDate.setText("");

        txtDueDate.setText("");

        txtFine.setText("0.00");

        cmbCondition.setSelectedIndex(0);

        txtSearch.setText("");

        dcReturnDate.setDate(null);

        table.clearSelection();

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            try{

                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName()
                );

            }catch(Exception ignored){}

            new ReturnBookFrame();

        });

    }
}