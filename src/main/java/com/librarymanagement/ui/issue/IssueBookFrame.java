package com.librarymanagement.ui.issue;

import com.librarymanagement.entity.Book;
import com.librarymanagement.entity.BookIssue;
import com.librarymanagement.entity.Member;
import com.librarymanagement.entity.User;
import com.librarymanagement.service.BookIssueService;
import com.librarymanagement.service.BookService;
import com.librarymanagement.service.MemberService;
import com.librarymanagement.service.impl.BookIssueServiceImpl;
import com.librarymanagement.service.impl.BookServiceImpl;
import com.librarymanagement.service.impl.MemberServiceImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Issue Book Screen.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public class IssueBookFrame extends JFrame {

    private final BookService bookService;
    private final MemberService memberService;
    private final BookIssueService issueService;

    private final JComboBox<Book> cmbBook;
    private final JComboBox<Member> cmbMember;

    private final JTextField txtIssueDate;
    private final JTextField txtDueDate;

    private final JButton btnIssue;
    private final JButton btnClear;
    private final JButton btnRefresh;

    private JTable table;
    private DefaultTableModel tableModel;

    private final User loggedInUser;

    public IssueBookFrame(User loggedInUser) {

        this.loggedInUser = loggedInUser;

        bookService = new BookServiceImpl();
        memberService = new MemberServiceImpl();
        issueService = new BookIssueServiceImpl();

        cmbBook = new JComboBox<>();
        cmbMember = new JComboBox<>();

        txtIssueDate = new JTextField(15);
        txtDueDate = new JTextField(15);

        btnIssue = new JButton("Issue Book");
        btnClear = new JButton("Clear");
        btnRefresh = new JButton("Refresh");

        initializeUI();

        initializeTable();

        registerEvents();

        loadBooks();

        loadMembers();

        loadIssuedBooks();

        setVisible(true);

    }

    private void initializeUI() {

        setTitle("Issue Book");

        setSize(1000, 650);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(
                new BorderLayout(10, 10)
        );

        mainPanel.setBorder(
                new EmptyBorder(15, 15, 15, 15)
        );

        JPanel formPanel = new JPanel(
                new GridBagLayout()
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets = new Insets(8, 8, 8, 8);

        gbc.anchor = GridBagConstraints.WEST;

        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;

        formPanel.add(
                new JLabel("Book"),
                gbc
        );

        gbc.gridx = 1;

        cmbBook.setPreferredSize(
                new Dimension(250, 28)
        );

        formPanel.add(
                cmbBook,
                gbc
        );

        gbc.gridx = 0;
        gbc.gridy++;

        formPanel.add(
                new JLabel("Member"),
                gbc
        );

        gbc.gridx = 1;

        cmbMember.setPreferredSize(
                new Dimension(250, 28)
        );

        formPanel.add(
                cmbMember,
                gbc
        );

        gbc.gridx = 0;
        gbc.gridy++;

        formPanel.add(
                new JLabel("Issue Date"),
                gbc
        );

        gbc.gridx = 1;

        txtIssueDate.setText(
                LocalDate.now().toString()
        );

        formPanel.add(
                txtIssueDate,
                gbc
        );

        gbc.gridx = 0;
        gbc.gridy++;

        formPanel.add(
                new JLabel("Due Date"),
                gbc
        );

        gbc.gridx = 1;

        txtDueDate.setText(
                LocalDate.now()
                        .plusDays(15)
                        .toString()
        );

        formPanel.add(
                txtDueDate,
                gbc
        );

        JPanel buttonPanel = new JPanel(
                new FlowLayout(
                        FlowLayout.CENTER,
                        15,
                        10
                )
        );

        buttonPanel.add(btnIssue);

        buttonPanel.add(btnClear);

        buttonPanel.add(btnRefresh);

        JPanel northPanel = new JPanel(
                new BorderLayout()
        );

        northPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        northPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        mainPanel.add(
                northPanel,
                BorderLayout.NORTH
        );

        add(mainPanel);

    }

    private void initializeTable() {

        tableModel = new DefaultTableModel(

                new Object[]{
                        "Issue ID",
                        "Book ID",
                        "Member ID",
                        "Issue Date",
                        "Due Date",
                        "Status"
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

        table.setRowHeight(28);

        table.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        table.setAutoCreateRowSorter(true);

        JScrollPane scrollPane =
                new JScrollPane(table);

        scrollPane.setBorder(
                BorderFactory.createTitledBorder(
                        "Issued Books"
                )
        );

        add(
                scrollPane,
                BorderLayout.CENTER
        );

    }

    private void registerEvents() {

        btnIssue.addActionListener(
                event -> issueBook()
        );

        btnRefresh.addActionListener(
                event -> loadIssuedBooks()
        );

        btnClear.addActionListener(
                event -> clearForm()
        );

        table.getSelectionModel()
                .addListSelectionListener(
                        event -> {

                            if (!event.getValueIsAdjusting()) {

                                fillFormFromTable();

                            }

                        }
                );

        addWindowListener(

                new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowOpened(
                            java.awt.event.WindowEvent e) {

                        loadBooks();

                        loadMembers();

                        loadIssuedBooks();

                    }

                }

        );

    }

    private void issueBook() {

        try {

            Book selectedBook =
                    (Book) cmbBook.getSelectedItem();

            Member selectedMember =
                    (Member) cmbMember.getSelectedItem();

            if (selectedBook == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a book."
                );

                return;

            }

            if (selectedMember == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a member."
                );

                return;

            }

            Optional<BookIssue> existingIssue =
                    issueService.findActiveIssue(

                            selectedMember.getMemberId(),

                            selectedBook.getBookId()

                    );

            if (existingIssue.isPresent()) {

                JOptionPane.showMessageDialog(

                        this,

                        "This member already has this book issued."

                );

                return;

            }

            BookIssue issue = new BookIssue();

            issue.setBook(selectedBook);

            issue.setMember(selectedMember);

            issue.setIssuedBy(loggedInUser);

            issue.setIssueDate(

                    LocalDate.parse(
                            txtIssueDate.getText().trim()
                    )

            );

            issue.setDueDate(

                    LocalDate.parse(
                            txtDueDate.getText().trim()
                    )

            );

            issue.setReturnDate(null);

            issue.setFineAmount(0.0);

            issue.setStatus("ISSUED");

            issueService.issueBook(issue);

            JOptionPane.showMessageDialog(

                    this,

                    "Book issued successfully."

            );

            loadIssuedBooks();

            clearForm();

        } catch (Exception exception) {

            JOptionPane.showMessageDialog(

                    this,

                    exception.getMessage(),

                    "Error",

                    JOptionPane.ERROR_MESSAGE

            );

        }

    }

    private void loadBooks() {

        cmbBook.removeAllItems();

        List<Book> books =
                bookService.findAll();

        for (Book book : books) {

            cmbBook.addItem(book);

        }

    }

    private void loadMembers() {

        cmbMember.removeAllItems();

        List<Member> members =
                memberService.findAll();

        for (Member member : members) {

            cmbMember.addItem(member);

        }

    }

    private void loadIssuedBooks() {

        tableModel.setRowCount(0);

        List<BookIssue> issues =
                issueService.findActiveIssues();

        for (BookIssue issue : issues) {

            tableModel.addRow(

                    new Object[]{

                            issue.getIssueId(),

                            issue.getBook() != null
                                    ? issue.getBook().getBookId()
                                    : "",

                            issue.getMember() != null
                                    ? issue.getMember().getMemberId()
                                    : "",

                            issue.getIssueDate(),

                            issue.getDueDate(),

                            issue.getStatus()

                    }

            );

        }

    }

    private void clearForm() {

        cmbBook.setSelectedIndex(-1);

        cmbMember.setSelectedIndex(-1);

        txtIssueDate.setText(
                LocalDate.now().toString()
        );

        txtDueDate.setText(
                LocalDate.now()
                        .plusDays(15)
                        .toString()
        );

        table.clearSelection();

    }

    private void fillFormFromTable() {

        int row = table.getSelectedRow();

        if (row == -1) {

            return;

        }

        Integer issueId = Integer.parseInt(
                table.getValueAt(row, 0).toString()
        );

        Optional<BookIssue> optionalIssue =
                issueService.findById(issueId);

        if (optionalIssue.isEmpty()) {

            return;

        }

        BookIssue issue =
                optionalIssue.get();

        if (issue.getBook() != null) {

            for (int i = 0; i < cmbBook.getItemCount(); i++) {

                Book book = cmbBook.getItemAt(i);

                if (book.getBookId().equals(
                        issue.getBook().getBookId())) {

                    cmbBook.setSelectedIndex(i);

                    break;

                }

            }

        }

        if (issue.getMember() != null) {

            for (int i = 0; i < cmbMember.getItemCount(); i++) {

                Member member = cmbMember.getItemAt(i);

                if (member.getMemberId().equals(
                        issue.getMember().getMemberId())) {

                    cmbMember.setSelectedIndex(i);

                    break;

                }

            }

        }

        txtIssueDate.setText(
                issue.getIssueDate().toString()
        );

        txtDueDate.setText(
                issue.getDueDate().toString()
        );

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            try {

                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName()
                );

            } catch (Exception ignored) {
            }

            /*
             * Temporary test user.
             * Replace with logged-in user after login integration.
             */
            User user = new User();

            user.setUserId(1);

            user.setUsername("admin");

            new IssueBookFrame(user);

        });

    }
}
