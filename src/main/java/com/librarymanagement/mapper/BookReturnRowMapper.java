package com.librarymanagement.mapper;

import com.librarymanagement.entity.BookIssue;
import com.librarymanagement.entity.BookReturn;
import com.librarymanagement.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Row Mapper for Book Return.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public final class BookReturnRowMapper {

    private BookReturnRowMapper() {
    }

    public static BookReturn map(ResultSet resultSet)
            throws SQLException {

        BookReturn bookReturn = new BookReturn();

        bookReturn.setReturnId(
                resultSet.getInt("return_id")
        );

        BookIssue issue = new BookIssue();

        issue.setIssueId(
                resultSet.getInt("issue_id")
        );

        bookReturn.setBookIssue(issue);

        User user = new User();

        user.setUserId(
                resultSet.getInt("received_by")
        );

        bookReturn.setReceivedBy(user);

        if (resultSet.getDate("return_date") != null) {

            bookReturn.setReturnDate(
                    resultSet.getDate("return_date")
                            .toLocalDate()
            );

        }

        bookReturn.setFineAmount(
                resultSet.getDouble("fine_amount")
        );

        bookReturn.setRemarks(
                resultSet.getString("remarks")
        );

        Timestamp created =
                resultSet.getTimestamp("created_at");

        if (created != null) {

            bookReturn.setCreatedAt(
                    created.toLocalDateTime()
            );

        }

        Timestamp updated =
                resultSet.getTimestamp("updated_at");

        if (updated != null) {

            bookReturn.setUpdatedAt(
                    updated.toLocalDateTime()
            );

        }

        return bookReturn;

    }

}