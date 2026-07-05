package com.librarymanagement.ui.books;

import com.librarymanagement.entity.Author;
import com.librarymanagement.entity.Book;
import com.librarymanagement.entity.Category;
import com.librarymanagement.entity.Publisher;
import com.librarymanagement.service.AuthorService;
import com.librarymanagement.service.BookService;
import com.librarymanagement.service.CategoryService;
import com.librarymanagement.service.PublisherService;
import com.librarymanagement.service.impl.AuthorServiceImpl;
import com.librarymanagement.service.impl.BookServiceImpl;
import com.librarymanagement.service.impl.CategoryServiceImpl;
import com.librarymanagement.service.impl.PublisherServiceImpl;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.Optional;

public class BookManagementFrame extends JFrame {

    /*====================================================
                      COLOR PALETTE
    ====================================================*/

    private static final Color PRIMARY =
            new Color(25,118,210);

    private static final Color PRIMARY_DARK =
            new Color(13,71,161);

    private static final Color SUCCESS =
            new Color(46,125,50);

    private static final Color DANGER =
            new Color(211,47,47);

    private static final Color WARNING =
            new Color(251,140,0);

    private static final Color LIGHT =
            new Color(245,247,250);

    private static final Color CARD =
            Color.WHITE;

    private static final Color BORDER =
            new Color(224,224,224);

    private static final Font TITLE_FONT =
            new Font("Segoe UI",Font.BOLD,30);

    private static final Font HEADER_FONT =
            new Font("Segoe UI",Font.BOLD,15);

    private static final Font NORMAL_FONT =
            new Font("Segoe UI",Font.PLAIN,14);

    /*====================================================
                        COMPONENTS
    ====================================================*/

    private JPanel rootPanel;
    private JPanel headerPanel;
    private JPanel centerPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel searchPanel;
    private JPanel buttonPanel;
    private JPanel statusPanel;

    private JLabel lblTitle;
    private JLabel lblSubtitle;
    private JLabel lblStatus;

    private JTextField txtIsbn = new JTextField();
    private JTextField txtTitle = new JTextField();
    private JTextField txtEdition = new JTextField();
    private JTextField txtLanguage = new JTextField();
    private JTextField txtPublishYear = new JTextField();
    private JTextField txtPages = new JTextField();
    private JTextArea txtDescription = new JTextArea();

    private JTextField txtSearch = new JTextField();

    private JComboBox<Category> cmbCategory =
            new JComboBox<>();

    private JComboBox<Author> cmbAuthor =
            new JComboBox<>();

    private JComboBox<Publisher> cmbPublisher =
            new JComboBox<>();

    private JButton btnAdd =
            new JButton("➕  Add");

    private JButton btnUpdate =
            new JButton("✏  Update");

    private JButton btnDelete =
            new JButton("🗑  Delete");

    private JButton btnClear =
            new JButton("🧹  Clear");

    private JButton btnSearch =
            new JButton("🔍 Search");

    private JTable table;

    private DefaultTableModel tableModel;

    /*====================================================
                       SERVICES
    ====================================================*/

    private final BookService bookService =
            new BookServiceImpl();

    private final CategoryService categoryService =
            new CategoryServiceImpl();

    private final AuthorService authorService =
            new AuthorServiceImpl();

    private final PublisherService publisherService =
            new PublisherServiceImpl();

    /*====================================================
                       CONSTRUCTOR
    ====================================================*/

    public BookManagementFrame() {

        initializeUI();

        initializeTable();

        registerEvents();

        loadCategories();

        loadAuthors();

        loadPublishers();

        loadBooks();

        setVisible(true);
    }

    /*====================================================
                    INITIALIZE UI
    ====================================================*/

    private void initializeUI() {

        setTitle("Library Management System - Book Management");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setSize(1550,900);

        setLocationRelativeTo(null);

        rootPanel = new JPanel(new BorderLayout(15,15));

        rootPanel.setBackground(LIGHT);

        rootPanel.setBorder(
                new EmptyBorder(15,15,15,15)
        );

        createHeader();

        createCenterLayout();

        createStatusBar();

        add(rootPanel);

    }
        /*====================================================
                    HEADER PANEL
    ====================================================*/

    private void createHeader() {

        headerPanel = new JPanel(new BorderLayout());

        headerPanel.setBackground(PRIMARY);

        headerPanel.setBorder(
                new EmptyBorder(20,25,20,25)
        );

        JPanel titlePanel = new JPanel();

        titlePanel.setOpaque(false);

        titlePanel.setLayout(
                new BoxLayout(titlePanel, BoxLayout.Y_AXIS)
        );

        lblTitle = new JLabel("📚  BOOK MANAGEMENT");

        lblTitle.setFont(TITLE_FONT);

        lblTitle.setForeground(Color.WHITE);

        lblSubtitle = new JLabel(
                "Manage Books • Search • Update • Delete • Inventory"
        );

        lblSubtitle.setFont(
                new Font("Segoe UI", Font.PLAIN, 15)
        );

        lblSubtitle.setForeground(
                new Color(235,240,255)
        );

        titlePanel.add(lblTitle);

        titlePanel.add(Box.createVerticalStrut(5));

        titlePanel.add(lblSubtitle);

        JLabel lblDate = new JLabel(
                java.time.LocalDate.now().toString()
        );

        lblDate.setFont(
                new Font("Segoe UI", Font.BOLD, 15)
        );

        lblDate.setForeground(Color.WHITE);

        headerPanel.add(titlePanel, BorderLayout.WEST);

        headerPanel.add(lblDate, BorderLayout.EAST);

        rootPanel.add(headerPanel, BorderLayout.NORTH);
    }

    /*====================================================
                    CENTER LAYOUT
    ====================================================*/

    private void createCenterLayout() {

        centerPanel = new JPanel(
                new BorderLayout(20,20)
        );

        centerPanel.setOpaque(false);

        leftPanel = createFormCard();

        rightPanel = new JPanel(
                new BorderLayout(15,15)
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

    /*====================================================
                    LEFT FORM CARD
    ====================================================*/

    private JPanel createFormCard() {

        JPanel card = new JPanel(
                new GridBagLayout()
        );

        card.setBackground(CARD);

        card.setPreferredSize(
                new Dimension(420,650)
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(BORDER,1,true),
                        new EmptyBorder(25,25,25,25)
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets = new Insets(8,8,8,8);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1;

        int row = 0;

        addField(card, gbc, row++,
                "ISBN *",
                txtIsbn);

        addField(card, gbc, row++,
                "Book Title *",
                txtTitle);

        addField(card, gbc, row++,
                "Category *",
                cmbCategory);

        addField(card, gbc, row++,
                "Author *",
                cmbAuthor);

        addField(card, gbc, row++,
                "Publisher *",
                cmbPublisher);

        addField(card, gbc, row++,
                "Edition",
                txtEdition);

        addField(card, gbc, row++,
                "Language",
                txtLanguage);

        addField(card, gbc, row++,
                "Publish Year",
                txtPublishYear);

        addField(card, gbc, row++,
                "Pages",
                txtPages);

        txtDescription.setRows(5);

        txtDescription.setLineWrap(true);

        txtDescription.setWrapStyleWord(true);

        txtDescription.setFont(NORMAL_FONT);

        JScrollPane descriptionPane =
                new JScrollPane(txtDescription);

        txtDescription.setPreferredSize(
                new Dimension(240, 180)
        );

        addField(card, gbc, row++,
                "Description",
                descriptionPane);

        createButtonPanel();

        gbc.gridx = 0;

        gbc.gridy = row;

        gbc.gridwidth = 2;

        gbc.fill = GridBagConstraints.HORIZONTAL;

        card.add(buttonPanel, gbc);

        return card;
    }

    /*====================================================
                ADD LABEL + COMPONENT
    ====================================================*/

    private void addField(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            String label,
            Component component) {

        JLabel lbl = new JLabel(label);

        lbl.setFont(HEADER_FONT);

        gbc.gridx = 0;

        gbc.gridy = row;

        gbc.weightx = 0;

        panel.add(lbl, gbc);

        styleComponent(component);

        gbc.gridx = 1;

        gbc.weightx = 1;

        panel.add(component, gbc);
    }

    /*====================================================
                    COMPONENT STYLING
    ====================================================*/

    private void styleComponent(Component component) {

        if(component instanceof JTextField field){

            field.setFont(NORMAL_FONT);

            field.setPreferredSize(
                    new Dimension(250,42)
            );
        }

        if(component instanceof JComboBox<?> combo){

            combo.setFont(NORMAL_FONT);

            combo.setPreferredSize(
                    new Dimension(250,40)
            );
        }

        if(component instanceof JScrollPane pane){

            pane.setBorder(
                    BorderFactory.createLineBorder(BORDER)
            );
        }
    }

    /*====================================================
                    SEARCH PANEL
    ====================================================*/

    private void createSearchPanel() {

        searchPanel = new JPanel(
                new BorderLayout(15,15)
        );

        searchPanel.setBackground(Color.WHITE);

        searchPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(BORDER,1,true),
                        new EmptyBorder(20,20,20,20)
                )
        );

        JLabel lblSearch = new JLabel(
                "🔍 Search Books"
        );

        lblSearch.setFont(
                new Font("Segoe UI",
                        Font.BOLD,
                        20)
        );

        txtSearch.setFont(NORMAL_FONT);

        txtSearch.setPreferredSize(
                new Dimension(300,40)
        );

        JPanel inputPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                15,
                                5
                        )
                );

        inputPanel.setOpaque(false);

        inputPanel.add(lblSearch);

        inputPanel.add(txtSearch);

        inputPanel.add(btnSearch);

        searchPanel.add(
                inputPanel,
                BorderLayout.CENTER
        );

        styleButtons();
    }

        /*====================================================
                    BUTTON PANEL
    ====================================================*/

    private void createButtonPanel() {

        buttonPanel = new JPanel(
                new GridLayout(3,2,12,12)
        );

        buttonPanel.setOpaque(false);

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);

        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        buttonPanel.add(btnSearch);

        JButton btnRefresh =
                new JButton("🔄 Refresh");

        styleButton(
                btnRefresh,
                new Color(0,150,136)
        );

        btnRefresh.addActionListener(e -> {

            txtSearch.setText("");

            clearForm();

            loadBooks();

            updateStatus(
                    "Book list refreshed."
            );

        });

        buttonPanel.add(btnRefresh);
    }

    /*====================================================
                    BUTTON STYLING
    ====================================================*/

    private void styleButtons() {

        styleButton(btnAdd,
                new Color(46,125,50));

        styleButton(btnUpdate,
                PRIMARY);

        styleButton(btnDelete,
                new Color(211,47,47));

        styleButton(btnClear,
                new Color(97,97,97));

        styleButton(btnSearch,
                new Color(21,101,192));
    }

    private void styleButton(
            JButton button,
            Color color) {

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
                new Dimension(160,42)
        );

        button.setOpaque(true);
    }

    /*====================================================
                    STATUS BAR
    ====================================================*/

    private void createStatusBar() {

        statusPanel =
                new JPanel(
                        new BorderLayout()
                );

        statusPanel.setBackground(
                PRIMARY_DARK
        );

        statusPanel.setBorder(
                new EmptyBorder(
                        8,
                        15,
                        8,
                        15
                )
        );

        lblStatus =
                new JLabel(
                        "Ready"
                );

        lblStatus.setForeground(
                Color.WHITE
        );

        lblStatus.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        JLabel lblVersion =
                new JLabel(
                        "Library Management System  |  Book Module"
                );

        lblVersion.setForeground(
                Color.WHITE
        );

        lblVersion.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        statusPanel.add(
                lblStatus,
                BorderLayout.WEST
        );

        statusPanel.add(
                lblVersion,
                BorderLayout.EAST
        );

        rootPanel.add(
                statusPanel,
                BorderLayout.SOUTH
        );
    }

    private void updateStatus(String message) {

        if (lblStatus != null) {

            lblStatus.setText(message);

        }
    }

    /*====================================================
                    TABLE INITIALIZATION
    ====================================================*/

    private void initializeTable() {

        tableModel = new DefaultTableModel(
                new Object[]{
                        "ID",
                        "ISBN",
                        "Title",
                        "Category",
                        "Author",
                        "Publisher",
                        "Edition",
                        "Language",
                        "Year"
                },
                0
        ) {

            @Override
            public boolean isCellEditable(
                    int row,
                    int column
            ) {
                return false;
            }

        };

        table = new JTable(tableModel);

        table.setFont(NORMAL_FONT);

        table.setRowHeight(36);

        table.setGridColor(
                new Color(235,235,235)
        );

        table.setShowVerticalLines(false);

        table.setSelectionBackground(
                new Color(187,222,251)
        );

        table.setSelectionForeground(
                Color.BLACK
        );

        table.setAutoCreateRowSorter(true);

        table.setFillsViewportHeight(true);

        table.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JTableHeader header =
                table.getTableHeader();

        header.setBackground(PRIMARY);

        header.setForeground(Color.WHITE);

        header.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        header.setPreferredSize(
                new Dimension(
                        100,
                        42
                )
        );

        header.setReorderingAllowed(false);

        JScrollPane scrollPane =
                new JScrollPane(table);

        scrollPane.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(BORDER,1,true),
                        new EmptyBorder(
                                10,
                                10,
                                10,
                                10
                        )
                )
        );

        rightPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );
    }

        /*====================================================
                    LOAD BOOKS
    ====================================================*/

    private void loadBooks() {

        tableModel.setRowCount(0);

        for (Book book : bookService.findAll()) {

            tableModel.addRow(
                    new Object[]{
                            book.getBookId(),
                            book.getIsbn(),
                            book.getTitle(),
                            book.getCategory().getCategoryName(),
                            book.getAuthor().getFullName(),
                            book.getPublisher().getPublisherName(),
                            book.getEdition(),
                            book.getLanguage(),
                            book.getPublishYear()
                    }
            );

        }

        updateStatus(
                "Total Books : " + tableModel.getRowCount()
        );
    }

    /*====================================================
                    CLEAR FORM
    ====================================================*/

    private void clearForm() {

        txtIsbn.setText("");

        txtTitle.setText("");

        txtEdition.setText("");

        txtLanguage.setText("");

        txtPublishYear.setText("");

        txtPages.setText("");

        txtDescription.setText("");

        txtSearch.setText("");

        cmbCategory.setSelectedIndex(-1);

        cmbAuthor.setSelectedIndex(-1);

        cmbPublisher.setSelectedIndex(-1);

        table.clearSelection();

        txtIsbn.requestFocus();

        updateStatus("Ready");
    }

    /*====================================================
                    REGISTER EVENTS
    ====================================================*/

    private void registerEvents() {

        btnAdd.addActionListener(e -> saveBook());

        btnUpdate.addActionListener(e -> updateBook());

        btnDelete.addActionListener(e -> deleteBook());

        btnSearch.addActionListener(e -> searchBook());

        btnClear.addActionListener(e -> clearForm());

        txtSearch.addActionListener(e -> searchBook());

        table.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                fillFormFromTable();

            }

        });

    }

    /*====================================================
                FILL FORM FROM TABLE
    ====================================================*/

    private void fillFormFromTable() {

        int row = table.getSelectedRow();

        if (row == -1) {

            return;

        }

        row = table.convertRowIndexToModel(row);

        txtIsbn.setText(
                tableModel.getValueAt(row,1).toString()
        );

        txtTitle.setText(
                tableModel.getValueAt(row,2).toString()
        );

        txtEdition.setText(
                tableModel.getValueAt(row,6).toString()
        );

        txtLanguage.setText(
                tableModel.getValueAt(row,7).toString()
        );

        txtPublishYear.setText(
                tableModel.getValueAt(row,8).toString()
        );

        updateStatus(
                "Selected : " +
                        tableModel.getValueAt(row,2)
        );

        /*
            Keep your existing logic here for selecting
            Category, Author and Publisher objects from
            the ComboBoxes if you already implemented it.
        */

    }

    /*====================================================
                    SEARCH BOOK
    ====================================================*/

    private void searchBook() {

        String keyword = txtSearch.getText().trim();

        tableModel.setRowCount(0);

        for (Book book : bookService.findByTitle(keyword)) {

            tableModel.addRow(
                    new Object[]{
                            book.getBookId(),
                            book.getIsbn(),
                            book.getTitle(),
                            book.getCategory().getCategoryName(),
                            book.getAuthor().getFullName(),
                            book.getPublisher().getPublisherName(),
                            book.getEdition(),
                            book.getLanguage(),
                            book.getPublishYear()
                    }
            );

        }

        updateStatus(
                tableModel.getRowCount()
                        + " record(s) found."
        );

    }

        /*====================================================
                    SAVE BOOK
    ====================================================*/

    private void saveBook() {

        try {

            if (txtIsbn.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "ISBN is required.",
                        "Validation",
                        JOptionPane.WARNING_MESSAGE
                );

                txtIsbn.requestFocus();
                return;
            }

            if (txtTitle.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Book Title is required.",
                        "Validation",
                        JOptionPane.WARNING_MESSAGE
                );

                txtTitle.requestFocus();
                return;
            }

            if (cmbCategory.getSelectedItem() == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select Category.",
                        "Validation",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            if (cmbAuthor.getSelectedItem() == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select Author.",
                        "Validation",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            if (cmbPublisher.getSelectedItem() == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select Publisher.",
                        "Validation",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            if (bookService.existsByIsbn(
                    txtIsbn.getText().trim())) {

                JOptionPane.showMessageDialog(
                        this,
                        "ISBN already exists.",
                        "Duplicate ISBN",
                        JOptionPane.WARNING_MESSAGE
                );

                txtIsbn.requestFocus();
                return;
            }

            Book book = new Book();

            book.setIsbn(
                    txtIsbn.getText().trim()
            );

            book.setTitle(
                    txtTitle.getText().trim()
            );

            book.setEdition(
                    txtEdition.getText().trim()
            );

            book.setLanguage(
                    txtLanguage.getText().trim()
            );

            if (!txtPublishYear.getText().trim().isEmpty()) {

                book.setPublishYear(
                        Integer.parseInt(
                                txtPublishYear.getText().trim()
                        )
                );
            }

            if (!txtPages.getText().trim().isEmpty()) {

                book.setTotalPages(
                        Integer.parseInt(
                                txtPages.getText().trim()
                        )
                );
            }

            book.setDescription(
                    txtDescription.getText().trim()
            );

            book.setCategory(
                    (Category) cmbCategory.getSelectedItem()
            );

            book.setAuthor(
                    (Author) cmbAuthor.getSelectedItem()
            );

            book.setPublisher(
                    (Publisher) cmbPublisher.getSelectedItem()
            );

            bookService.save(book);

            JOptionPane.showMessageDialog(
                    this,
                    "Book added successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadBooks();

            clearForm();

            updateStatus(
                    "Book added successfully."
            );

        }

        catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Publish Year and Pages must be numeric.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE
            );

        }

        catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        }

    }

    /*====================================================
                    UPDATE BOOK
    ====================================================*/

    private void updateBook() {

        int row = table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a book first.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        row = table.convertRowIndexToModel(row);

        try {

            Integer id = Integer.parseInt(
                    tableModel.getValueAt(row,0).toString()
            );

            Optional<Book> optionalBook =
                    bookService.findById(id);

            if (optionalBook.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Book not found."
                );

                return;
            }

            Book book = optionalBook.get();

            book.setIsbn(txtIsbn.getText().trim());
            book.setTitle(txtTitle.getText().trim());
            book.setEdition(txtEdition.getText().trim());
            book.setLanguage(txtLanguage.getText().trim());

            if (!txtPublishYear.getText().trim().isEmpty()) {

                book.setPublishYear(
                        Integer.parseInt(
                                txtPublishYear.getText().trim()
                        )
                );

            }

            if (!txtPages.getText().trim().isEmpty()) {

                book.setTotalPages(
                        Integer.parseInt(
                                txtPages.getText().trim()
                        )
                );

            }

            book.setDescription(
                    txtDescription.getText().trim()
            );

            book.setCategory(
                    (Category) cmbCategory.getSelectedItem()
            );

            book.setAuthor(
                    (Author) cmbAuthor.getSelectedItem()
            );

            book.setPublisher(
                    (Publisher) cmbPublisher.getSelectedItem()
            );

            if (bookService.update(book)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Book updated successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                loadBooks();

                clearForm();

                updateStatus(
                        "Book updated successfully."
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Unable to update book.",
                        "Failed",
                        JOptionPane.ERROR_MESSAGE
                );

            }

        }

        catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Publish Year and Pages must be numeric.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE
            );

        }

        catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        }

    }
        /*====================================================
                    DELETE BOOK
    ====================================================*/

    private void deleteBook() {

        int row = table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a book to delete.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        row = table.convertRowIndexToModel(row);

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this book?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (choice != JOptionPane.YES_OPTION) {

            return;
        }

        try {

            Integer bookId = Integer.parseInt(
                    tableModel.getValueAt(row,0).toString()
            );

            if (bookService.delete(bookId)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Book deleted successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                loadBooks();

                clearForm();

                updateStatus(
                        "Book deleted successfully."
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Unable to delete book.",
                        "Delete Failed",
                        JOptionPane.ERROR_MESSAGE
                );

            }

        }

        catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        }

    }

    /*====================================================
                    LOAD CATEGORIES
    ====================================================*/

    private void loadCategories() {

        cmbCategory.removeAllItems();

        for (Category category : categoryService.findAll()) {

            cmbCategory.addItem(category);

        }

    }

    /*====================================================
                    LOAD AUTHORS
    ====================================================*/

    private void loadAuthors() {

        cmbAuthor.removeAllItems();

        for (Author author : authorService.findAll()) {

            cmbAuthor.addItem(author);

        }

    }

    /*====================================================
                    LOAD PUBLISHERS
    ====================================================*/

    private void loadPublishers() {

        cmbPublisher.removeAllItems();

        for (Publisher publisher : publisherService.findAll()) {

            cmbPublisher.addItem(publisher);

        }

    }

    /*====================================================
                    WINDOW ENTRY POINT
    ====================================================*/

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            try {

                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName()
                );

            } catch (Exception ignored) {
            }

            new BookManagementFrame();

        });

    }

}