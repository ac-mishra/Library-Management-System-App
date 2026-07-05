package com.librarymanagement.ui.fine;

import com.librarymanagement.entity.BookIssue;
import com.librarymanagement.service.BookIssueService;
import com.librarymanagement.service.impl.BookIssueServiceImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Optional;

public class FineManagementFrame extends JFrame {

    private static final Color PRIMARY =
            new Color(25,118,210);

    private static final Color SUCCESS =
            new Color(46,125,50);

    private static final Color DANGER =
            new Color(211,47,47);

    private static final Color LIGHT =
            new Color(245,247,250);

    private static final Font TITLE_FONT =
            new Font("Segoe UI",Font.BOLD,28);

    private static final Font LABEL_FONT =
            new Font("Segoe UI",Font.BOLD,14);

    private static final Font FIELD_FONT =
            new Font("Segoe UI",Font.PLAIN,14);

    private JPanel rootPanel;

    private JPanel leftPanel;

    private JPanel rightPanel;

    private JPanel searchPanel;

    private JPanel buttonPanel;

    private JComboBox<BookIssue> cmbIssue =
            new JComboBox<>();

    private JTextField txtMember =
            new JTextField();

    private JTextField txtBook =
            new JTextField();

    private JTextField txtFine =
            new JTextField();

    private JTextField txtStatus =
            new JTextField();

    private JTextField txtSearch =
            new JTextField();

    private JButton btnPay =
            new JButton("💰 Pay Fine");

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

    private final BookIssueService issueService =
            new BookIssueServiceImpl();

    public FineManagementFrame(){

        initializeUI();

        initializeTable();

        registerEvents();

        loadFineRecords();

        setVisible(true);

    }

    private void initializeUI(){

        setTitle("Fine Management");

        setSize(1500,900);

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

        createCenterLayout();

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
                        "💰 FINE MANAGEMENT"
                );

        title.setForeground(Color.WHITE);

        title.setFont(TITLE_FONT);

        JLabel date =
                new JLabel(
                        java.time.LocalDate.now().toString()
                );

        date.setForeground(Color.WHITE);

        header.add(title,BorderLayout.WEST);

        header.add(date,BorderLayout.EAST);

        rootPanel.add(header,BorderLayout.NORTH);

    }
    private void createCenterLayout() {

        JPanel center =
                new JPanel(
                        new BorderLayout(
                                20,
                                20
                        )
                );

        center.setOpaque(false);

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

        center.add(
                leftPanel,
                BorderLayout.WEST
        );

        center.add(
                rightPanel,
                BorderLayout.CENTER
        );

        rootPanel.add(
                center,
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
                        650
                )
        );

        panel.setBackground(Color.WHITE);

        panel.setBorder(
                BorderFactory.createCompoundBorder(

                        new LineBorder(
                                new Color(
                                        220,
                                        220,
                                        220
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
                "Issue",
                cmbIssue
        );

        txtMember.setEditable(false);

        addField(
                panel,
                gbc,
                row++,
                "Member",
                txtMember
        );

        txtBook.setEditable(false);

        addField(
                panel,
                gbc,
                row++,
                "Book",
                txtBook
        );

        txtFine.setEditable(false);

        addField(
                panel,
                gbc,
                row++,
                "Fine Amount",
                txtFine
        );

        txtStatus.setEditable(false);

        addField(
                panel,
                gbc,
                row++,
                "Payment Status",
                txtStatus
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
            Component component){

        JLabel lbl =
                new JLabel(label);

        lbl.setFont(LABEL_FONT);

        gbc.gridx = 0;

        gbc.gridy = row;

        gbc.weightx = 0;

        panel.add(lbl,gbc);

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

        panel.add(component,gbc);

    }

    private void createSearchPanel(){

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
                                new Color(
                                        220,
                                        220,
                                        220
                                ),
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
                        "🔍 Search Fine Records"
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

        styleButton(btnPay,SUCCESS);

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

            loadFineRecords();

        });

        buttonPanel.add(btnPay);

        buttonPanel.add(btnUpdate);

        buttonPanel.add(btnDelete);

        buttonPanel.add(btnClear);

        buttonPanel.add(btnSearch);

        buttonPanel.add(btnRefresh);

    }

    private void styleButtons(){

        styleButton(btnPay,SUCCESS);

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

                                "Return Date",

                                "Fine",

                                "Status"

                        },

                        0

                ){

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column){

                        return false;

                    }

                };

        table =
                new JTable(tableModel);

        table.setFont(FIELD_FONT);

        table.setRowHeight(36);

        table.setShowGrid(false);

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
                                new Color(
                                        220,
                                        220,
                                        220
                                ),
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

        btnPay.addActionListener(
                e -> payFine()
        );

        btnUpdate.addActionListener(
                e -> updateFine()
        );

        btnDelete.addActionListener(
                e -> deleteFine()
        );

        btnClear.addActionListener(
                e -> clearForm()
        );

        btnSearch.addActionListener(
                e -> searchFine()
        );

        txtSearch.addActionListener(
                e -> searchFine()
        );

        cmbIssue.addActionListener(
                e -> loadFineDetails()
        );

        table.getSelectionModel()
                .addListSelectionListener(
                        e ->{

                            if(!e.getValueIsAdjusting()){

                                fillFormFromTable();

                            }

                        }
                );

    }

    private void loadFineRecords(){

        tableModel.setRowCount(0);

        cmbIssue.removeAllItems();

        for(BookIssue issue : issueService.findAll()){

            if(issue.getFineAmount()!=null
                    && issue.getFineAmount()>0){

                cmbIssue.addItem(issue);

                tableModel.addRow(

                        new Object[]{

                                issue.getIssueId(),

                                issue.getBook().getTitle(),

                                issue.getMember().getFirstName()
                                        +" "
                                        +issue.getMember().getLastName(),

                                issue.getReturnDate(),

                                issue.getFineAmount(),

                                issue.getStatus()

                        }

                );

            }

        }

    }
    private void loadFineDetails() {

        if (cmbIssue.getSelectedItem() == null) {

            txtBook.setText("");

            txtMember.setText("");

            txtFine.setText("");

            txtStatus.setText("");

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

        txtFine.setText(
                String.format(
                        "%.2f",
                        issue.getFineAmount()
                )
        );

        txtStatus.setText(
                issue.getStatus()
        );

    }

    private void payFine() {

        if (cmbIssue.getSelectedItem() == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a record."
            );

            return;

        }

        BookIssue issue =
                (BookIssue) cmbIssue.getSelectedItem();

        if (issue.getFineAmount() == null
                || issue.getFineAmount() <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "No fine available."
            );

            return;

        }

        int option =
                JOptionPane.showConfirmDialog(

                        this,

                        "Mark fine as paid?",

                        "Confirm",

                        JOptionPane.YES_NO_OPTION

                );

        if (option != JOptionPane.YES_OPTION) {

            return;

        }

        issue.setFineAmount(0.0);

        issue.setStatus("RETURNED");

        if (issueService.update(issue)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Fine paid successfully."
            );

            clearForm();

            loadFineRecords();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to update record."
            );

        }

    }

    private void updateFine() {

        int row =
                table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a record."
            );

            return;

        }

        row =
                table.convertRowIndexToModel(row);

        Integer issueId =
                Integer.parseInt(
                        tableModel.getValueAt(
                                row,
                                0
                        ).toString()
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

        BookIssue issue =
                optional.get();

        issue.setFineAmount(

                Double.parseDouble(
                        txtFine.getText()
                )

        );

        if (issueService.update(issue)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Fine updated successfully."
            );

            clearForm();

            loadFineRecords();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to update fine."
            );

        }

    }
    private void deleteFine() {

        int row = table.getSelectedRow();

        if (row == -1) {

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

                        "Delete selected fine record?",

                        "Confirm Delete",

                        JOptionPane.YES_NO_OPTION,

                        JOptionPane.WARNING_MESSAGE

                );

        if (option != JOptionPane.YES_OPTION) {

            return;

        }

        Integer issueId =
                Integer.parseInt(
                        tableModel.getValueAt(
                                row,
                                0
                        ).toString()
                );

        if (issueService.delete(issueId)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Fine record deleted successfully."
            );

            clearForm();

            loadFineRecords();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to delete fine record."
            );

        }

    }

    private void searchFine() {

        String keyword =
                txtSearch.getText()
                        .trim()
                        .toLowerCase();

        tableModel.setRowCount(0);

        for (BookIssue issue : issueService.findAll()) {

            if (issue.getFineAmount() == null
                    || issue.getFineAmount() <= 0) {

                continue;

            }

            String book =
                    issue.getBook()
                            .getTitle()
                            .toLowerCase();

            String member =
                    (issue.getMember()
                            .getFirstName()
                            + " "
                            + issue.getMember()
                            .getLastName())
                            .toLowerCase();

            if (book.contains(keyword)
                    || member.contains(keyword)) {

                tableModel.addRow(new Object[]{

                        issue.getIssueId(),

                        issue.getBook().getTitle(),

                        issue.getMember().getFirstName()
                                + " "
                                + issue.getMember().getLastName(),

                        issue.getReturnDate(),

                        issue.getFineAmount(),

                        issue.getStatus()

                });

            }

        }

    }

    private void fillFormFromTable() {

        int row = table.getSelectedRow();

        if (row == -1) {

            return;

        }

        row = table.convertRowIndexToModel(row);

        Integer issueId =
                Integer.parseInt(
                        tableModel.getValueAt(
                                row,
                                0
                        ).toString()
                );

        Optional<BookIssue> optional =
                issueService.findById(issueId);

        if (optional.isEmpty()) {

            return;

        }

        BookIssue issue =
                optional.get();

        cmbIssue.setSelectedItem(issue);

        loadFineDetails();

    }

    private void clearForm() {

        cmbIssue.setSelectedIndex(-1);

        txtBook.setText("");

        txtMember.setText("");

        txtFine.setText("");

        txtStatus.setText("");

        txtSearch.setText("");

        table.clearSelection();

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            try {

                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName()
                );

            } catch (Exception ignored) {
            }

            new FineManagementFrame();

        });

    }

}