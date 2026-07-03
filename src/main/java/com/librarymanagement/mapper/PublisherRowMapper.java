package com.librarymanagement.mapper;

import com.librarymanagement.entity.Publisher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Maps ResultSet to Publisher.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public final class PublisherRowMapper {

    private PublisherRowMapper() {
    }

    public static Publisher map(ResultSet resultSet)
            throws SQLException {

        Publisher publisher = new Publisher();

        publisher.setPublisherId(
                resultSet.getInt("publisher_id")
        );

        publisher.setPublisherName(
                resultSet.getString("publisher_name")
        );

        publisher.setEmail(
                resultSet.getString("email")
        );

        publisher.setPhone(
                resultSet.getString("phone")
        );

        publisher.setAddress(
                resultSet.getString("address")
        );

        publisher.setWebsite(
                resultSet.getString("website")
        );

        Timestamp created =
                resultSet.getTimestamp("created_at");

        if (created != null) {

            publisher.setCreatedAt(
                    created.toLocalDateTime()
            );

        }

        return publisher;

    }

}