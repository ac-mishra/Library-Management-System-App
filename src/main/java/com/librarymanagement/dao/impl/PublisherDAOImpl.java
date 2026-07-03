package com.librarymanagement.dao.impl;

import com.librarymanagement.common.constants.PublisherQueries;
import com.librarymanagement.config.DBConnection;
import com.librarymanagement.entity.Publisher;
import com.librarymanagement.mapper.PublisherRowMapper;
import com.librarymanagement.repository.PublisherRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Publisher DAO Implementation.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public class PublisherDAOImpl
        implements PublisherRepository {

    @Override
    public List<Publisher> findAll() {

        List<Publisher> publishers =
                new ArrayList<>();

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                PublisherQueries.FIND_ALL
                        );

                ResultSet resultSet =
                        preparedStatement.executeQuery()

        ) {

            while (resultSet.next()) {

                publishers.add(
                        PublisherRowMapper.map(resultSet)
                );

            }

        } catch (SQLException exception) {

            throw new RuntimeException(exception);

        }

        return publishers;

    }

    @Override
    public Optional<Publisher> findById(Integer id) {

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                PublisherQueries.FIND_BY_ID
                        )

        ) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet =
                         preparedStatement.executeQuery()) {

                if (resultSet.next()) {

                    return Optional.of(
                            PublisherRowMapper.map(resultSet)
                    );

                }

            }

        } catch (SQLException exception) {

            throw new RuntimeException(exception);

        }

        return Optional.empty();

    }

    @Override
    public Publisher save(Publisher publisher) {

        throw new UnsupportedOperationException();

    }

    @Override
    public boolean update(Publisher publisher) {

        throw new UnsupportedOperationException();

    }

    @Override
    public boolean delete(Integer id) {

        throw new UnsupportedOperationException();

    }

}