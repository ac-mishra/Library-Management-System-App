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
 * @version 1.0.0
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

        Book book = new Book();

        book.setBookId(
                resultSet.getInt("book_id")
        );

        issue.setBook(book);

        Member member = new Member();

        member.setMemberId(
                resultSet.getInt("member_id")
        );

        issue.setMember(member);

        User user = new User();

        user.setUserId(
                resultSet.getInt("issued_by")
        );

        issue.setIssuedBy(user);

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

        issue.setFineAmount(
                resultSet.getDouble("fine_amount")
        );

        issue.setStatus(
                resultSet.getString("status")
        );

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