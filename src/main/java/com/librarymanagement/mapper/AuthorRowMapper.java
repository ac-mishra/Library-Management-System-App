package com.librarymanagement.mapper;

import com.librarymanagement.entity.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Maps ResultSet to Author.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public final class AuthorRowMapper {

    private AuthorRowMapper() {
    }

    public static Author map(ResultSet resultSet)
            throws SQLException {

        Author author = new Author();

        author.setAuthorId(
                resultSet.getInt("author_id")
        );

        author.setFirstName(
                resultSet.getString("first_name")
        );

        author.setLastName(
                resultSet.getString("last_name")
        );

        author.setEmail(
                resultSet.getString("email")
        );

        author.setPhone(
                resultSet.getString("phone")
        );

        author.setCountry(
                resultSet.getString("country")
        );

        author.setBiography(
                resultSet.getString("biography")
        );

        Timestamp created =
                resultSet.getTimestamp("created_at");

        if (created != null) {

            author.setCreatedAt(
                    created.toLocalDateTime()
            );

        }

        return author;

    }

}