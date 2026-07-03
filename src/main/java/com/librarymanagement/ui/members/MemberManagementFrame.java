package com.librarymanagement.ui.members;

import com.librarymanagement.service.MemberService;
import com.librarymanagement.service.impl.MemberServiceImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.Optional;
import com.librarymanagement.entity.Member;

public class MemberManagementFrame extends JFrame {

    private final JTextField txtMemberCode =
            new JTextField();

    private final JTextField txtFirstName =
            new JTextField();

    private final JTextField txtLastName =
            new JTextField();

    private final JComboBox<String> cmbGender =
            new JComboBox<>(
                    new String[]{
                            "Male",
                            "Female",
                            "Other"
                    });

    private final JTextField txtEmail =
            new JTextField();

    private final JTextField txtPhone =
            new JTextField();

    private final JTextField txtAddress =
            new JTextField();

    private final JTextField txtDob =
            new JTextField();

    private final JTextField txtJoinDate =
            new JTextField();

    private final JComboBox<String> cmbStatus =
            new JComboBox<>(
                    new String[]{
                            "ACTIVE",
                            "INACTIVE"
                    });

    private final JTextField txtSearch =
            new JTextField(20);

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

    private JPanel mainPanel;

    private final MemberService memberService =
            new MemberServiceImpl();

    public MemberManagementFrame() {

        initializeUI();

        initializeTable();

        registerEvents();

        loadMembers();

    }

    /**
     * Initialize User Interface.
     */
    private void initializeUI() {

        setTitle("Member Management");

        setSize(1300, 750);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        mainPanel =
                new JPanel(
                        new BorderLayout(10, 10)
                );

        mainPanel.setBorder(
                new EmptyBorder(15, 15, 15, 15)
        );

        JLabel lblTitle =
                new JLabel(
                        "MEMBER MANAGEMENT",
                        SwingConstants.CENTER
                );

        lblTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        26
                )
        );

        mainPanel.add(
                lblTitle,
                BorderLayout.NORTH
        );

        JPanel formPanel =
                new JPanel(
                        new GridBagLayout()
                );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(8, 8, 8, 8);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;

        formPanel.add(
                new JLabel("Member Code"),
                gbc
        );

        gbc.gridx = 1;

        formPanel.add(
                txtMemberCode,
                gbc
        );

        gbc.gridx = 2;

        formPanel.add(
                new JLabel("First Name"),
                gbc
        );

        gbc.gridx = 3;

        formPanel.add(
                txtFirstName,
                gbc
        );

        gbc.gridx = 0;
        gbc.gridy++;

        formPanel.add(
                new JLabel("Last Name"),
                gbc
        );

        gbc.gridx = 1;

        formPanel.add(
                txtLastName,
                gbc
        );

        gbc.gridx = 2;

        formPanel.add(
                new JLabel("Gender"),
                gbc
        );

        gbc.gridx = 3;

        formPanel.add(
                cmbGender,
                gbc
        );

        gbc.gridx = 0;
        gbc.gridy++;

        formPanel.add(
                new JLabel("Email"),
                gbc
        );

        gbc.gridx = 1;

        formPanel.add(
                txtEmail,
                gbc
        );

        gbc.gridx = 2;

        formPanel.add(
                new JLabel("Phone"),
                gbc
        );

        gbc.gridx = 3;

        formPanel.add(
                txtPhone,
                gbc
        );

        gbc.gridx = 0;
        gbc.gridy++;

        formPanel.add(
                new JLabel("Address"),
                gbc
        );

        gbc.gridx = 1;

        formPanel.add(
                txtAddress,
                gbc
        );

        gbc.gridx = 2;

        formPanel.add(
                new JLabel("Date Of Birth"),
                gbc
        );

        gbc.gridx = 3;

        formPanel.add(
                txtDob,
                gbc
        );

        gbc.gridx = 0;
        gbc.gridy++;

        formPanel.add(
                new JLabel("Join Date"),
                gbc
        );

        gbc.gridx = 1;

        formPanel.add(
                txtJoinDate,
                gbc
        );

        gbc.gridx = 2;

        formPanel.add(
                new JLabel("Status"),
                gbc
        );

        gbc.gridx = 3;

        formPanel.add(
                cmbStatus,
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

        buttonPanel.add(
                new JLabel("Search")
        );

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
                        "Member Code",
                        "First Name",
                        "Last Name",
                        "Gender",
                        "Email",
                        "Phone",
                        "Status"
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

        JScrollPane scrollPane =
                new JScrollPane(table);

        mainPanel.add(
                scrollPane,
                BorderLayout.SOUTH
        );

    }

    /**
     * Registers all events.
     */
    private void registerEvents() {

        btnAdd.addActionListener(
                event -> saveMember()
        );

        btnUpdate.addActionListener(
                event -> updateMember()
        );

        btnDelete.addActionListener(
                event -> deleteMember()
        );

        btnClear.addActionListener(
                event -> clearForm()
        );

        btnSearch.addActionListener(
                event -> searchMember()
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
     * Loads all members.
     */
    private void loadMembers() {

        tableModel.setRowCount(0);

        for (Member member :
                memberService.findAll()) {

            tableModel.addRow(

                    new Object[]{

                            member.getMemberId(),

                            member.getMemberCode(),

                            member.getFirstName(),

                            member.getLastName(),

                            member.getGender(),

                            member.getEmail(),

                            member.getPhone(),

                            member.getMembershipStatus()

                    }

            );

        }

    }

    /**
     * Clears form.
     */
    private void clearForm() {

        txtMemberCode.setText("");

        txtFirstName.setText("");

        txtLastName.setText("");

        cmbGender.setSelectedIndex(0);

        txtEmail.setText("");

        txtPhone.setText("");

        txtAddress.setText("");

        txtDob.setText("");

        txtJoinDate.setText("");

        cmbStatus.setSelectedIndex(0);

        txtSearch.setText("");

        table.clearSelection();

        txtMemberCode.requestFocus();

    }


    /**
     * Saves a new member.
     */
    private void saveMember() {

        try {

            if (txtMemberCode.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Member Code is required."
                );

                txtMemberCode.requestFocus();

                return;

            }

            if (txtFirstName.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "First Name is required."
                );

                txtFirstName.requestFocus();

                return;

            }

            if (memberService.existsByMemberCode(
                    txtMemberCode.getText().trim())) {

                JOptionPane.showMessageDialog(
                        this,
                        "Member Code already exists."
                );

                return;

            }

            Member member = new Member();

            member.setMemberCode(
                    txtMemberCode.getText().trim()
            );

            member.setFirstName(
                    txtFirstName.getText().trim()
            );

            member.setLastName(
                    txtLastName.getText().trim()
            );

            member.setGender(
                    cmbGender.getSelectedItem().toString()
            );

            member.setEmail(
                    txtEmail.getText().trim()
            );

            member.setPhone(
                    txtPhone.getText().trim()
            );

            member.setAddress(
                    txtAddress.getText().trim()
            );

            if (!txtDob.getText().trim().isEmpty()) {

                member.setDateOfBirth(
                        LocalDate.parse(
                                txtDob.getText().trim()
                        )
                );

            }

            if (!txtJoinDate.getText().trim().isEmpty()) {

                member.setJoinDate(
                        LocalDate.parse(
                                txtJoinDate.getText().trim()
                        )
                );

            }

            member.setMembershipStatus(
                    cmbStatus.getSelectedItem().toString()
            );

            memberService.save(member);

            JOptionPane.showMessageDialog(
                    this,
                    "Member added successfully."
            );

            clearForm();

            loadMembers();

        } catch (Exception exception) {

            JOptionPane.showMessageDialog(
                    this,
                    exception.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        }

    }

    /**
     * Searches member by member code.
     */
    private void searchMember() {

        String memberCode =
                txtSearch.getText().trim();

        tableModel.setRowCount(0);

        Optional<Member> optionalMember =
                memberService.findByMemberCode(
                        memberCode
                );

        if (optionalMember.isPresent()) {

            Member member =
                    optionalMember.get();

            tableModel.addRow(

                    new Object[]{

                            member.getMemberId(),

                            member.getMemberCode(),

                            member.getFirstName(),

                            member.getLastName(),

                            member.getGender(),

                            member.getEmail(),

                            member.getPhone(),

                            member.getMembershipStatus()

                    }

            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Member not found."
            );

        }

    }

    /**
     * Updates selected member.
     */
    private void updateMember() {

        int row =
                table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a member."
            );

            return;

        }

        try {

            Integer memberId =
                    Integer.parseInt(
                            table.getValueAt(
                                    row,
                                    0
                            ).toString()
                    );

            Optional<Member> optionalMember =
                    memberService.findById(
                            memberId
                    );

            if (optionalMember.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Member not found."
                );

                return;

            }

            Member member =
                    optionalMember.get();

            member.setMemberCode(
                    txtMemberCode.getText().trim()
            );

            member.setFirstName(
                    txtFirstName.getText().trim()
            );

            member.setLastName(
                    txtLastName.getText().trim()
            );

            member.setGender(
                    cmbGender.getSelectedItem().toString()
            );

            member.setEmail(
                    txtEmail.getText().trim()
            );

            member.setPhone(
                    txtPhone.getText().trim()
            );

            member.setAddress(
                    txtAddress.getText().trim()
            );

            if (!txtDob.getText().trim().isEmpty()) {

                member.setDateOfBirth(
                        LocalDate.parse(
                                txtDob.getText().trim()
                        )
                );

            }

            if (!txtJoinDate.getText().trim().isEmpty()) {

                member.setJoinDate(
                        LocalDate.parse(
                                txtJoinDate.getText().trim()
                        )
                );

            }

            member.setMembershipStatus(
                    cmbStatus.getSelectedItem().toString()
            );

            if (memberService.update(member)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Member updated successfully."
                );

                clearForm();

                loadMembers();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Unable to update member."
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

    /**
     * Deletes selected member.
     */
    private void deleteMember() {

        int row =
                table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a member."
            );

            return;

        }

        int choice =
                JOptionPane.showConfirmDialog(

                        this,

                        "Delete selected member?",

                        "Confirm",

                        JOptionPane.YES_NO_OPTION,

                        JOptionPane.WARNING_MESSAGE

                );

        if (choice != JOptionPane.YES_OPTION) {

            return;

        }

        Integer memberId =
                Integer.parseInt(
                        table.getValueAt(
                                row,
                                0
                        ).toString()
                );

        if (memberService.delete(memberId)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Member deleted successfully."
            );

            clearForm();

            loadMembers();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to delete member."
            );

        }

    }

    private void fillFormFromTable() {

        int row = table.getSelectedRow();

        if (row == -1) {
            return;
        }

        Integer memberId =
                Integer.parseInt(
                        table.getValueAt(row, 0).toString()
                );

        Optional<Member> optionalMember =
                memberService.findById(memberId);

        if (optionalMember.isEmpty()) {
            return;
        }

        Member member =
                optionalMember.get();

        txtMemberCode.setText(
                member.getMemberCode()
        );

        txtFirstName.setText(
                member.getFirstName()
        );

        txtLastName.setText(
                member.getLastName()
        );

        cmbGender.setSelectedItem(
                member.getGender()
        );

        txtEmail.setText(
                member.getEmail()
        );

        txtPhone.setText(
                member.getPhone()
        );

        txtAddress.setText(
                member.getAddress()
        );

        if (member.getDateOfBirth() != null) {

            txtDob.setText(
                    member.getDateOfBirth().toString()
            );

        } else {

            txtDob.setText("");

        }

        if (member.getJoinDate() != null) {

            txtJoinDate.setText(
                    member.getJoinDate().toString()
            );

        } else {

            txtJoinDate.setText("");

        }

        cmbStatus.setSelectedItem(
                member.getMembershipStatus()
        );

    }
}

