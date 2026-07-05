package com.librarymanagement.ui.publishers;

import com.librarymanagement.entity.Publisher;
import com.librarymanagement.service.PublisherService;
import com.librarymanagement.service.impl.PublisherServiceImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PublisherManagementFrame extends JFrame {

    private static final Color PRIMARY =
            new Color(25,118,210);

    private static final Color BACKGROUND =
            new Color(245,247,250);

    private static final Color BORDER =
            new Color(220,220,220);

    private static final Font TITLE_FONT =
            new Font("Segoe UI",Font.BOLD,28);

    private static final Font LABEL_FONT =
            new Font("Segoe UI",Font.BOLD,14);

    private static final Font FIELD_FONT =
            new Font("Segoe UI",Font.PLAIN,14);

    private JPanel rootPanel;
    private JPanel centerPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel searchPanel;
    private JPanel buttonPanel;

    private JTextField txtPublisherName =
            new JTextField();

    private JTextField txtEmail =
            new JTextField();

    private JTextField txtPhone =
            new JTextField();

    private JTextField txtWebsite =
            new JTextField();

    private JTextArea txtAddress =
            new JTextArea();

    private JTextField txtSearch =
            new JTextField();

    private JButton btnAdd =
            new JButton("➕ Add");

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

    private final PublisherService publisherService =
            new PublisherServiceImpl();

    public PublisherManagementFrame() {

        initializeUI();

        initializeTable();

        registerEvents();

        loadPublishers();

        setVisible(true);

    }

    private void initializeUI() {

        setTitle("Publisher Management");

        setSize(1450,850);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        rootPanel =
                new JPanel(
                        new BorderLayout(15,15)
                );

        rootPanel.setBackground(BACKGROUND);

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
                        "🏢 PUBLISHER MANAGEMENT"
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
                        420,
                        650
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

        addField(panel,gbc,row++,"Publisher Name",txtPublisherName);

        addField(panel,gbc,row++,"Email",txtEmail);

        addField(panel,gbc,row++,"Phone",txtPhone);

        addField(panel,gbc,row++,"Website",txtWebsite);

        txtAddress.setRows(5);

        txtAddress.setLineWrap(true);

        txtAddress.setWrapStyleWord(true);

        txtAddress.setFont(FIELD_FONT);

        JScrollPane address =
                new JScrollPane(txtAddress);

        addField(panel,gbc,row++,"Address",address);

        createButtonPanel();

        gbc.gridx=0;

        gbc.gridy=row;

        gbc.gridwidth=2;

        panel.add(buttonPanel,gbc);

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

        gbc.gridx=0;

        gbc.gridy=row;

        gbc.weightx=0;

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

        gbc.gridx=1;

        gbc.weightx=1;

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
                        "🔍 Search Publisher"
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
                        38
                )
        );

        searchPanel.add(lbl);

        searchPanel.add(txtSearch);

        searchPanel.add(btnSearch);

        styleButtons();

    }

    private void createButtonPanel() {

        buttonPanel = new JPanel(
                new GridLayout(3,2,12,12)
        );

        buttonPanel.setOpaque(false);

        styleButton(btnAdd,new Color(46,125,50));
        styleButton(btnUpdate,PRIMARY);
        styleButton(btnDelete,new Color(211,47,47));
        styleButton(btnClear,new Color(97,97,97));
        styleButton(btnSearch,new Color(21,101,192));

        JButton btnRefresh =
                new JButton("🔄 Refresh");

        styleButton(
                btnRefresh,
                new Color(0,150,136)
        );

        btnRefresh.addActionListener(e -> {

            txtSearch.setText("");

            clearForm();

            loadPublishers();

        });

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);

        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        buttonPanel.add(btnSearch);
        buttonPanel.add(btnRefresh);

    }

    private void styleButtons() {

        styleButton(btnAdd,new Color(46,125,50));
        styleButton(btnUpdate,PRIMARY);
        styleButton(btnDelete,new Color(211,47,47));
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

        button.setForeground(Color.WHITE);

        button.setBackground(color);

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
                                "ID",
                                "Publisher Name",
                                "Email",
                                "Phone",
                                "Website"
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
                new Color(220,235,252)
        );

        table.setSelectionForeground(
                Color.BLACK
        );

        table.setShowVerticalLines(false);

        table.setAutoCreateRowSorter(true);

        table.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        table.getTableHeader().setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        table.getTableHeader().setBackground(PRIMARY);

        table.getTableHeader().setForeground(Color.WHITE);

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

        btnAdd.addActionListener(e -> savePublisher());

        btnUpdate.addActionListener(e -> updatePublisher());

        btnDelete.addActionListener(e -> deletePublisher());

        btnClear.addActionListener(e -> clearForm());

        btnSearch.addActionListener(e -> searchPublisher());

        txtSearch.addActionListener(e -> searchPublisher());

        table.getSelectionModel()
                .addListSelectionListener(e -> {

                    if(!e.getValueIsAdjusting()){

                        fillFormFromTable();

                    }

                });

    }
    private void loadPublishers() {

        tableModel.setRowCount(0);

        for (Publisher publisher : publisherService.findAll()) {

            tableModel.addRow(new Object[]{

                    publisher.getPublisherId(),

                    publisher.getPublisherName(),

                    publisher.getEmail(),

                    publisher.getPhone(),

                    publisher.getWebsite()

            });

        }

    }

    private void savePublisher() {

        try {

            if (txtPublisherName.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Publisher Name is required."
                );

                txtPublisherName.requestFocus();

                return;

            }

            Publisher publisher = new Publisher();

            publisher.setPublisherName(
                    txtPublisherName.getText().trim()
            );

            publisher.setEmail(
                    txtEmail.getText().trim()
            );

            publisher.setPhone(
                    txtPhone.getText().trim()
            );

            publisher.setAddress(
                    txtAddress.getText().trim()
            );

            publisher.setWebsite(
                    txtWebsite.getText().trim()
            );

            publisherService.save(publisher);

            JOptionPane.showMessageDialog(
                    this,
                    "Publisher added successfully."
            );

            clearForm();

            loadPublishers();

        } catch (Exception exception) {

            JOptionPane.showMessageDialog(
                    this,
                    exception.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        }

    }

    private void updatePublisher() {

        int row = table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a publisher."
            );

            return;

        }

        row = table.convertRowIndexToModel(row);

        Integer id = Integer.parseInt(
                tableModel.getValueAt(row,0).toString()
        );

        Publisher publisher =
                publisherService.findById(id).orElse(null);

        if (publisher == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Publisher not found."
            );

            return;

        }

        publisher.setPublisherName(
                txtPublisherName.getText().trim()
        );

        publisher.setEmail(
                txtEmail.getText().trim()
        );

        publisher.setPhone(
                txtPhone.getText().trim()
        );

        publisher.setAddress(
                txtAddress.getText().trim()
        );

        publisher.setWebsite(
                txtWebsite.getText().trim()
        );

        if (publisherService.update(publisher)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Publisher updated successfully."
            );

            clearForm();

            loadPublishers();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to update publisher."
            );

        }

    }

    private void deletePublisher() {

        int row = table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a publisher."
            );

            return;

        }

        row = table.convertRowIndexToModel(row);

        Integer id = Integer.parseInt(
                tableModel.getValueAt(row,0).toString()
        );

        int option = JOptionPane.showConfirmDialog(

                this,

                "Delete selected publisher?",

                "Confirm Delete",

                JOptionPane.YES_NO_OPTION

        );

        if (option != JOptionPane.YES_OPTION) {

            return;

        }

        if (publisherService.delete(id)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Publisher deleted successfully."
            );

            clearForm();

            loadPublishers();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to delete publisher."
            );

        }

    }

    private void searchPublisher() {

        String keyword =
                txtSearch.getText()
                        .trim()
                        .toLowerCase();

        tableModel.setRowCount(0);

        for (Publisher publisher : publisherService.findAll()) {

            if (publisher.getPublisherName()
                    .toLowerCase()
                    .contains(keyword)) {

                tableModel.addRow(new Object[]{

                        publisher.getPublisherId(),

                        publisher.getPublisherName(),

                        publisher.getEmail(),

                        publisher.getPhone(),

                        publisher.getWebsite()

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

        Integer id = Integer.parseInt(
                tableModel.getValueAt(row,0).toString()
        );

        Publisher publisher =
                publisherService.findById(id).orElse(null);

        if (publisher == null) {

            return;

        }

        txtPublisherName.setText(
                publisher.getPublisherName()
        );

        txtEmail.setText(
                publisher.getEmail()
        );

        txtPhone.setText(
                publisher.getPhone()
        );

        txtAddress.setText(
                publisher.getAddress()
        );

        txtWebsite.setText(
                publisher.getWebsite()
        );

    }

    private void clearForm() {

        txtPublisherName.setText("");

        txtEmail.setText("");

        txtPhone.setText("");

        txtAddress.setText("");

        txtWebsite.setText("");

        txtSearch.setText("");

        table.clearSelection();

        txtPublisherName.requestFocus();

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            try {

                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName()
                );

            } catch (Exception ignored) {
            }

            new PublisherManagementFrame();

        });

    }
}