package com.librarymanagement.ui.authors;

import com.librarymanagement.entity.Author;
import com.librarymanagement.service.AuthorService;
import com.librarymanagement.service.impl.AuthorServiceImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AuthorManagementFrame extends JFrame {

    private static final Color PRIMARY = new Color(25, 118, 210);
    private static final Color BACKGROUND = new Color(245, 247, 250);
    private static final Color BORDER = new Color(220, 220, 220);

    private JTextField txtFirstName = new JTextField();
    private JTextField txtLastName = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JTextField txtPhone = new JTextField();
    private JTextField txtCountry = new JTextField();
    private JTextArea txtBiography = new JTextArea();
    private JTextField txtSearch = new JTextField();

    private JButton btnAdd = new JButton("➕ Add");
    private JButton btnUpdate = new JButton("✏ Update");
    private JButton btnDelete = new JButton("🗑 Delete");
    private JButton btnClear = new JButton("🧹 Clear");
    private JButton btnSearch = new JButton("🔍 Search");

    private JTable table;
    private DefaultTableModel tableModel;

    private final AuthorService authorService =
            new AuthorServiceImpl();

    public AuthorManagementFrame() {

        initializeUI();

        initializeTable();

        registerEvents();

        loadAuthors();

        setVisible(true);
    }

    private void initializeUI() {

        setTitle("Author Management");

        setSize(1450, 850);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel root = new JPanel(new BorderLayout(15, 15));

        root.setBackground(BACKGROUND);

        root.setBorder(new EmptyBorder(15, 15, 15, 15));

        //---------------- HEADER ----------------//

        JPanel header = new JPanel(new BorderLayout());

        header.setBackground(PRIMARY);

        header.setBorder(new EmptyBorder(20, 25, 20, 25));

        JLabel title = new JLabel("✍ AUTHOR MANAGEMENT");

        title.setForeground(Color.WHITE);

        title.setFont(new Font("Segoe UI", Font.BOLD, 28));

        header.add(title, BorderLayout.WEST);

        JLabel date = new JLabel(java.time.LocalDate.now().toString());

        date.setForeground(Color.WHITE);

        date.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        header.add(date, BorderLayout.EAST);

        root.add(header, BorderLayout.NORTH);

        //---------------- CENTER ----------------//

        JPanel center = new JPanel(new BorderLayout(20, 20));

        center.setOpaque(false);

        JPanel form = createFormPanel();

        JPanel right = new JPanel(new BorderLayout(15, 15));

        right.setOpaque(false);

        JPanel search = createSearchPanel();

        right.add(search, BorderLayout.NORTH);

        center.add(form, BorderLayout.WEST);

        center.add(right, BorderLayout.CENTER);

        root.add(center, BorderLayout.CENTER);

        setContentPane(root);
    }

    private JPanel createFormPanel() {

        JPanel panel = new JPanel(new GridBagLayout());

        panel.setBackground(Color.WHITE);

        panel.setPreferredSize(new Dimension(420, 650));

        panel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(BORDER, 1, true),
                new EmptyBorder(20, 20, 20, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(8, 8, 8, 8);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1;

        int row = 0;

        addField(panel, gbc, row++, "First Name", txtFirstName);

        addField(panel, gbc, row++, "Last Name", txtLastName);

        addField(panel, gbc, row++, "Email", txtEmail);

        addField(panel, gbc, row++, "Phone", txtPhone);

        addField(panel, gbc, row++, "Country", txtCountry);

        txtBiography.setRows(5);

        txtBiography.setLineWrap(true);

        txtBiography.setWrapStyleWord(true);

        JScrollPane bio = new JScrollPane(txtBiography);

        addField(panel, gbc, row++, "Biography", bio);

        JPanel buttons = new JPanel(new GridLayout(3, 2, 10, 10));

        buttons.setOpaque(false);

        buttons.add(btnAdd);
        buttons.add(btnUpdate);
        buttons.add(btnDelete);
        buttons.add(btnClear);
        buttons.add(btnSearch);

        JButton refresh = new JButton("🔄 Refresh");

        buttons.add(refresh);

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;

        panel.add(buttons, gbc);

        return panel;
    }

    private JPanel createSearchPanel() {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));

        panel.setBackground(Color.WHITE);

        panel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(BORDER, 1, true),
                new EmptyBorder(10, 10, 10, 10)
        ));

        JLabel lbl = new JLabel("🔍 Search Author");

        lbl.setFont(new Font("Segoe UI", Font.BOLD, 18));

        txtSearch.setPreferredSize(new Dimension(300, 38));

        panel.add(lbl);

        panel.add(txtSearch);

        panel.add(btnSearch);

        return panel;
    }

    private void addField(JPanel panel,
                          GridBagConstraints gbc,
                          int row,
                          String label,
                          Component component) {

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;

        JLabel lbl = new JLabel(label);

        lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));

        panel.add(lbl, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;

        if (component instanceof JTextField field) {

            field.setPreferredSize(new Dimension(240, 38));

            field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        }

        panel.add(component, gbc);
    }

    private void initializeTable() {

        tableModel = new DefaultTableModel(
                new Object[]{
                        "ID",
                        "First Name",
                        "Last Name",
                        "Email",
                        "Phone",
                        "Country"
                }, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        table = new JTable(tableModel);

        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        table.setRowHeight(36);

        table.setSelectionBackground(
                new Color(220, 235, 252)
        );

        table.setSelectionForeground(Color.BLACK);

        table.setShowVerticalLines(false);

        table.setAutoCreateRowSorter(true);

        table.getTableHeader().setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        table.getTableHeader().setBackground(PRIMARY);

        table.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scroll =
                new JScrollPane(table);

        scroll.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(BORDER, 1, true),
                new EmptyBorder(10, 10, 10, 10)
        ));

        Container root = getContentPane();

        JPanel center =
                (JPanel) ((BorderLayout) root.getLayout())
                        .getLayoutComponent(BorderLayout.CENTER);

        JPanel right =
                (JPanel) center.getComponent(1);

        right.add(scroll, BorderLayout.CENTER);

    }

    private void registerEvents() {

        btnAdd.addActionListener(e -> saveAuthor());

        btnUpdate.addActionListener(e -> updateAuthor());

        btnDelete.addActionListener(e -> deleteAuthor());

        btnClear.addActionListener(e -> clearForm());

        btnSearch.addActionListener(e -> searchAuthor());

        txtSearch.addActionListener(e -> searchAuthor());

        table.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                fillFormFromTable();

            }

        });

    }

    private void loadAuthors() {

        tableModel.setRowCount(0);

        for (Author author : authorService.findAll()) {

            tableModel.addRow(new Object[]{

                    author.getAuthorId(),

                    author.getFirstName(),

                    author.getLastName(),

                    author.getEmail(),

                    author.getPhone(),

                    author.getCountry()

            });

        }

    }

    private void clearForm() {

        txtFirstName.setText("");

        txtLastName.setText("");

        txtEmail.setText("");

        txtPhone.setText("");

        txtCountry.setText("");

        txtBiography.setText("");

        txtSearch.setText("");

        table.clearSelection();

        txtFirstName.requestFocus();

    }

    private void fillFormFromTable() {

        int row = table.getSelectedRow();

        if (row == -1) {
            return;
        }

        row = table.convertRowIndexToModel(row);

        Integer id =
                Integer.parseInt(
                        tableModel.getValueAt(row, 0).toString()
                );

        Author author =
                authorService.findById(id).orElse(null);

        if (author == null) {
            return;
        }

        txtFirstName.setText(author.getFirstName());

        txtLastName.setText(author.getLastName());

        txtEmail.setText(author.getEmail());

        txtPhone.setText(author.getPhone());

        txtCountry.setText(author.getCountry());

        txtBiography.setText(author.getBiography());

    }

    private void saveAuthor() {

        try {

            if (txtFirstName.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "First Name is required."
                );

                txtFirstName.requestFocus();

                return;
            }

            Author author = new Author();

            author.setFirstName(
                    txtFirstName.getText().trim()
            );

            author.setLastName(
                    txtLastName.getText().trim()
            );

            author.setEmail(
                    txtEmail.getText().trim()
            );

            author.setPhone(
                    txtPhone.getText().trim()
            );

            author.setCountry(
                    txtCountry.getText().trim()
            );

            author.setBiography(
                    txtBiography.getText().trim()
            );

            authorService.save(author);

            JOptionPane.showMessageDialog(
                    this,
                    "Author added successfully."
            );

            clearForm();

            loadAuthors();

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        }

    }

    private void updateAuthor() {

        int row = table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an author."
            );

            return;

        }

        row = table.convertRowIndexToModel(row);

        Integer id = Integer.parseInt(
                tableModel.getValueAt(row, 0).toString()
        );

        Author author =
                authorService.findById(id).orElse(null);

        if (author == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Author not found."
            );

            return;

        }

        author.setFirstName(txtFirstName.getText().trim());

        author.setLastName(txtLastName.getText().trim());

        author.setEmail(txtEmail.getText().trim());

        author.setPhone(txtPhone.getText().trim());

        author.setCountry(txtCountry.getText().trim());

        author.setBiography(txtBiography.getText().trim());

        if (authorService.update(author)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Author updated successfully."
            );

            clearForm();

            loadAuthors();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to update author."
            );

        }

    }

    private void deleteAuthor() {

        int row = table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an author."
            );

            return;

        }

        row = table.convertRowIndexToModel(row);

        int option = JOptionPane.showConfirmDialog(

                this,

                "Delete selected author?",

                "Confirm",

                JOptionPane.YES_NO_OPTION

        );

        if (option != JOptionPane.YES_OPTION) {

            return;

        }

        Integer id = Integer.parseInt(
                tableModel.getValueAt(row, 0).toString()
        );

        if (authorService.delete(id)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Author deleted successfully."
            );

            clearForm();

            loadAuthors();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to delete author."
            );

        }

    }

    private void searchAuthor() {

        String keyword =
                txtSearch.getText().trim().toLowerCase();

        tableModel.setRowCount(0);

        for (Author author : authorService.findAll()) {

            if (author.getFullName()
                    .toLowerCase()
                    .contains(keyword)) {

                tableModel.addRow(new Object[]{

                        author.getAuthorId(),

                        author.getFirstName(),

                        author.getLastName(),

                        author.getEmail(),

                        author.getPhone(),

                        author.getCountry()

                });

            }

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

            new AuthorManagementFrame();

        });

    }
}