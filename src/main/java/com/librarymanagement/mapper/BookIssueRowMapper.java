package com.librarymanagement.mapper;

import com.librarymanagement.entity.Book;
import com.librarymanagement.entity.BookIssue;
import com.librarymanagement.entity.Member;
import com.librarymanagement.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Row Mapper for Book Issue.
 *
 * @author Amrit Chandan Mishra
 * @version 2.0.0
 */
public final class BookIssueRowMapper {

    private BookIssueRowMapper() {
    }

    public static BookIssue map(ResultSet resultSet)
            throws SQLException {

        BookIssue issue = new BookIssue();

        issue.setIssueId(
                resultSet.getInt("issue_id")
        );

        //==========================
        // Book
        //==========================

        Book book = new Book();

        book.setBookId(
                resultSet.getInt("book_id")
        );

        try {

            book.setTitle(
                    resultSet.getString("title")
            );

        } catch (SQLException ignored) {
        }

        try {

            book.setIsbn(
                    resultSet.getString("isbn")
            );

        } catch (SQLException ignored) {
        }

        issue.setBook(book);

        //==========================
        // Member
        //==========================

        Member member = new Member();

        member.setMemberId(
                resultSet.getInt("member_id")
        );

        try {

            member.setFirstName(
                    resultSet.getString("first_name")
            );

        } catch (SQLException ignored) {
        }

        try {

            member.setLastName(
                    resultSet.getString("last_name")
            );

        } catch (SQLException ignored) {
        }

        issue.setMember(member);

        //==========================
        // User
        //==========================

        User user = new User();

        user.setUserId(
                resultSet.getInt("issued_by")
        );

        issue.setIssuedBy(user);

        //==========================
        // Dates
        //==========================

        if (resultSet.getDate("issue_date") != null) {

            issue.setIssueDate(
                    resultSet.getDate("issue_date")
                            .toLocalDate()
            );

        }

        if (resultSet.getDate("due_date") != null) {

            issue.setDueDate(
                    resultSet.getDate("due_date")
                            .toLocalDate()
            );

        }

        if (resultSet.getDate("return_date") != null) {

            issue.setReturnDate(
                    resultSet.getDate("return_date")
                            .toLocalDate()
            );

        }

        //==========================
        // Fine & Status
        //==========================

        issue.setFineAmount(
                resultSet.getDouble("fine_amount")
        );

        issue.setStatus(
                resultSet.getString("status")
        );

        //==========================
        // Audit
        //==========================

        Timestamp created =
                resultSet.getTimestamp("created_at");

        if (created != null) {

            issue.setCreatedAt(
                    created.toLocalDateTime()
            );

        }

        Timestamp updated =
                resultSet.getTimestamp("updated_at");

        if (updated != null) {

            issue.setUpdatedAt(
                    updated.toLocalDateTime()
            );

        }

        return issue;

    }

}