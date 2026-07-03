package com.librarymanagement.ui.books;

import com.librarymanagement.entity.Author;
import com.librarymanagement.entity.Category;
import com.librarymanagement.entity.Publisher;
import com.librarymanagement.service.BookService;
import com.librarymanagement.service.impl.BookServiceImpl;
import com.librarymanagement.entity.Book;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Optional;

import com.librarymanagement.service.AuthorService;
import com.librarymanagement.service.CategoryService;
import com.librarymanagement.service.PublisherService;

import com.librarymanagement.service.impl.AuthorServiceImpl;
import com.librarymanagement.service.impl.CategoryServiceImpl;
import com.librarymanagement.service.impl.PublisherServiceImpl;

/**
 * Book Management Screen.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public class BookManagementFrame extends JFrame {

    private final JTextField txtIsbn =
            new JTextField();

    private final JTextField txtTitle =
            new JTextField();

    private final JTextField txtEdition =
            new JTextField();

    private final JTextField txtLanguage =
            new JTextField();

    private final JTextField txtPublishYear =
            new JTextField();

    private final JTextField txtPages =
            new JTextField();

    private final JTextArea txtDescription =
            new JTextArea();

    private final JTextField txtSearch =
            new JTextField(20);

    private final JComboBox<Category> cmbCategory =
            new JComboBox<>();

    private final JComboBox<Author> cmbAuthor =
            new JComboBox<>();

    private final JComboBox<Publisher> cmbPublisher =
            new JComboBox<>();

    private final JButton btnAdd =
            new JButton("Add");

    private final JButton btnUpdate =
            new JButton("Update");

    private final JButton btnDelete =
            new JButton("Delete");

    private final JButton btnClear =
            new JButton("Clear");

    private final JButton btnSearch =
            new JButton("Search");

    private JTable table;

    private DefaultTableModel tableModel;

    private final BookService bookService =
            new BookServiceImpl();

    private final CategoryService categoryService =
            new CategoryServiceImpl();

    private final AuthorService authorService =
            new AuthorServiceImpl();

    private final PublisherService publisherService =
            new PublisherServiceImpl();

    private JPanel mainPanel;

    public BookManagementFrame() {

        initializeUI();

        initializeTable();

        registerEvents();

        loadCategories();

        loadAuthors();

        loadPublishers();

        loadBooks();

    }

    /**
     * Initializes the user interface.
     */
    private void initializeUI() {

        setTitle("Book Management");

        setSize(1300, 750);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        mainPanel = new JPanel(
                new BorderLayout(10, 10)
        );

        mainPanel.setBorder(
                new EmptyBorder(15, 15, 15, 15)
        );

        JLabel lblTitle =
                new JLabel(
                        "BOOK MANAGEMENT",
                        SwingConstants.CENTER
                );

        lblTitle.setFont(
                new Font("Segoe UI", Font.BOLD, 26)
        );

        mainPanel.add(
                lblTitle,
                BorderLayout.NORTH
        );

        JPanel formPanel =
                new JPanel(new GridBagLayout());

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(8, 8, 8, 8);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;

        formPanel.add(
                new JLabel("ISBN"),
                gbc
        );

        gbc.gridx = 1;

        txtIsbn.setColumns(20);

        formPanel.add(
                txtIsbn,
                gbc
        );

        gbc.gridx = 2;

        formPanel.add(
                new JLabel("Title"),
                gbc
        );

        gbc.gridx = 3;

        formPanel.add(
                txtTitle,
                gbc
        );

        gbc.gridx = 0;
        gbc.gridy++;

        formPanel.add(
                new JLabel("Category"),
                gbc
        );

        gbc.gridx = 1;

        formPanel.add(
                cmbCategory,
                gbc
        );

        gbc.gridx = 2;

        formPanel.add(
                new JLabel("Author"),
                gbc
        );

        gbc.gridx = 3;

        formPanel.add(
                cmbAuthor,
                gbc
        );

        gbc.gridx = 0;
        gbc.gridy++;

        formPanel.add(
                new JLabel("Publisher"),
                gbc
        );

        gbc.gridx = 1;

        formPanel.add(
                cmbPublisher,
                gbc
        );

        gbc.gridx = 2;

        formPanel.add(
                new JLabel("Edition"),
                gbc
        );

        gbc.gridx = 3;

        formPanel.add(
                txtEdition,
                gbc
        );

        gbc.gridx = 0;
        gbc.gridy++;

        formPanel.add(
                new JLabel("Language"),
                gbc
        );

        gbc.gridx = 1;

        formPanel.add(
                txtLanguage,
                gbc
        );

        gbc.gridx = 2;

        formPanel.add(
                new JLabel("Publish Year"),
                gbc
        );

        gbc.gridx = 3;

        formPanel.add(
                txtPublishYear,
                gbc
        );

        gbc.gridx = 0;
        gbc.gridy++;

        formPanel.add(
                new JLabel("Pages"),
                gbc
        );

        gbc.gridx = 1;

        formPanel.add(
                txtPages,
                gbc
        );

        gbc.gridx = 2;

        formPanel.add(
                new JLabel("Description"),
                gbc
        );

        gbc.gridx = 3;

        JScrollPane descriptionScroll =
                new JScrollPane(
                        txtDescription
                );

        descriptionScroll.setPreferredSize(
                new Dimension(250, 80)
        );

        formPanel.add(
                descriptionScroll,
                gbc
        );

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                10
                        )
                );

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        buttonPanel.add(new JLabel("Search"));

        buttonPanel.add(txtSearch);

        buttonPanel.add(btnSearch);
        JPanel topPanel =
                new JPanel(
                        new BorderLayout()
                );

        topPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        topPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        mainPanel.add(
                topPanel,
                BorderLayout.CENTER
        );

        add(mainPanel);

    }

    /**
     * Initializes JTable.
     */
    private void initializeTable() {

        tableModel = new DefaultTableModel();

        tableModel.setColumnIdentifiers(
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
                }
        );

        table = new JTable(tableModel);

        table.setRowHeight(28);
        table.setAutoCreateRowSorter(true);

        table.getTableHeader()
                .setReorderingAllowed(false);

        table.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        table.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane =
                new JScrollPane(table);

        mainPanel.add(
                scrollPane,
                BorderLayout.SOUTH
        );

    }

    /**
     * Loads all books into JTable.
     */
    private void loadBooks() {

        tableModel.setRowCount(0);

        for (Book book :
                bookService.findAll()) {

            tableModel.addRow(

                    new Object[]{

                            book.getBookId(),

                            book.getIsbn(),

                            book.getTitle(),

                            book.getCategory()
                                    .getCategoryName(),

                            book.getAuthor()
                                    .getFullName(),

                            book.getPublisher()
                                    .getPublisherName(),

                            book.getEdition(),

                            book.getLanguage(),

                            book.getPublishYear()

                    }

            );

        }

    }

    /**
     * Clears all input fields.
     */
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

    }
    /**
     * Register button events.
     */
    private void registerEvents() {

        btnClear.addActionListener(
                event -> clearForm()
        );

        btnSearch.addActionListener(
                event -> searchBook()
        );

        btnAdd.addActionListener(
                event -> saveBook()
        );

        btnUpdate.addActionListener(
                event -> updateBook()
        );

        btnDelete.addActionListener(
                event -> deleteBook()
        );



        table.getSelectionModel()
                .addListSelectionListener(
                        event -> {

                            if (!event.getValueIsAdjusting()) {

                                fillFormFromTable();

                            }

                        }
                );

    }

    /**
     * Loads selected book into form.
     */
    private void fillFormFromTable() {

        int row = table.getSelectedRow();

        if (row == -1) {
            return;
        }

        txtIsbn.setText(
                table.getValueAt(row, 1).toString()
        );

        txtTitle.setText(
                table.getValueAt(row, 2).toString()
        );

        txtEdition.setText(
                table.getValueAt(row, 6).toString()
        );

        txtLanguage.setText(
                table.getValueAt(row, 7).toString()
        );

        txtPublishYear.setText(
                table.getValueAt(row, 8).toString()
        );

        /*
         * ComboBox selection will be implemented
         * after Author, Category and Publisher
         * Management screens are completed.
         */
    }

    /**
     * Saves a new book.
     */
    private void saveBook() {

        try {

            if (txtIsbn.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "ISBN is required."
                );

                txtIsbn.requestFocus();
                return;

            }

            if (txtTitle.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Book title is required."
                );

                txtTitle.requestFocus();
                return;

            }

            if (cmbCategory.getSelectedItem() == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a category."
                );

                return;

            }

            if (cmbAuthor.getSelectedItem() == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select an author."
                );

                return;

            }

            if (cmbPublisher.getSelectedItem() == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a publisher."
                );

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

            if (bookService.existsByIsbn(book.getIsbn())) {

                JOptionPane.showMessageDialog(
                        this,
                        "ISBN already exists."
                );

                return;

            }

            bookService.save(book);

            JOptionPane.showMessageDialog(
                    this,
                    "Book added successfully."
            );

            clearForm();

            loadBooks();

        }

        catch (NumberFormatException exception) {

            JOptionPane.showMessageDialog(
                    this,
                    "Publish Year and Pages must be numeric."
            );

        }

        catch (Exception exception) {

            JOptionPane.showMessageDialog(
                    this,
                    exception.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        }

    }


    /**
     * Searches books by title.
     */
    private void searchBook() {

        String keyword =
                txtSearch.getText().trim();

        tableModel.setRowCount(0);

        for (Book book :
                bookService.findByTitle(keyword)) {

            tableModel.addRow(

                    new Object[]{

                            book.getBookId(),

                            book.getIsbn(),

                            book.getTitle(),

                            book.getCategory()
                                    .getCategoryName(),

                            book.getAuthor()
                                    .getFullName(),

                            book.getPublisher()
                                    .getPublisherName(),

                            book.getEdition(),

                            book.getLanguage(),

                            book.getPublishYear()

                    }

            );

        }

    }
    /**
     * Updates the selected book.
     */
    private void updateBook() {

        int row = table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a book to update."
            );

            return;

        }

        try {

            Integer bookId = Integer.parseInt(
                    table.getValueAt(row, 0).toString()
            );

            Optional<Book> optionalBook =
                    bookService.findById(bookId);

            if (optionalBook.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Book not found."
                );

                return;

            }

            Book book = optionalBook.get();

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

            if (bookService.update(book)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Book updated successfully."
                );

                clearForm();

                loadBooks();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Book update failed."
                );

            }

        }

        catch (NumberFormatException exception) {

            JOptionPane.showMessageDialog(
                    this,
                    "Publish Year and Pages must be numeric."
            );

        }

        catch (Exception exception) {

            JOptionPane.showMessageDialog(
                    this,
                    exception.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        }

    }

    /**
     * Deletes the selected book.
     */
    private void deleteBook() {

        int row = table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a book to delete."
            );

            return;

        }

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

                    table.getValueAt(row, 0).toString()

            );

            if (bookService.delete(bookId)) {

                JOptionPane.showMessageDialog(

                        this,

                        "Book deleted successfully."

                );

                clearForm();

                loadBooks();

            }

            else {

                JOptionPane.showMessageDialog(

                        this,

                        "Unable to delete book."

                );

            }

        }

        catch (Exception exception) {

            JOptionPane.showMessageDialog(

                    this,

                    exception.getMessage(),

                    "Error",

                    JOptionPane.ERROR_MESSAGE

            );

        }

    }
        private void loadCategories() {

            cmbCategory.removeAllItems();

            for (Category category :
                    categoryService.findAll()) {

                cmbCategory.addItem(category);

            }

        }
    private void loadAuthors() {

        cmbAuthor.removeAllItems();

        for (Author author :
                authorService.findAll()) {

            cmbAuthor.addItem(author);

        }

    }

    private void loadPublishers() {

        cmbPublisher.removeAllItems();

        for (Publisher publisher :
                publisherService.findAll()) {

            cmbPublisher.addItem(publisher);

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

            new BookManagementFrame().setVisible(true);

        });

    }

}