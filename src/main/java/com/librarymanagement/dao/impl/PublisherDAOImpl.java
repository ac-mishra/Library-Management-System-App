package com.librarymanagement.dao.impl;

import com.librarymanagement.common.constants.PublisherQueries;
import com.librarymanagement.config.DBConnection;
import com.librarymanagement.entity.Publisher;
import com.librarymanagement.mapper.PublisherRowMapper;
import com.librarymanagement.repository.PublisherRepository;

import javax.swing.*;
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

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                PublisherQueries.INSERT,
                                PreparedStatement.RETURN_GENERATED_KEYS
                        )

        ) {

            preparedStatement.setString(1,publisher.getPublisherName());
            preparedStatement.setString(2,publisher.getEmail());
            preparedStatement.setString(3,publisher.getPhone());
            preparedStatement.setString(4,publisher.getAddress());
            preparedStatement.setString(5,publisher.getWebsite());

            preparedStatement.executeUpdate();

            try(ResultSet rs = preparedStatement.getGeneratedKeys()){

                if(rs.next()){

                    publisher.setPublisherId(rs.getInt(1));

                }

            }

            return publisher;

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    @Override
    public boolean update(Publisher publisher) {

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                PublisherQueries.UPDATE
                        )

        ) {

            preparedStatement.setString(1,publisher.getPublisherName());
            preparedStatement.setString(2,publisher.getEmail());
            preparedStatement.setString(3,publisher.getPhone());
            preparedStatement.setString(4,publisher.getAddress());
            preparedStatement.setString(5,publisher.getWebsite());
            preparedStatement.setInt(6,publisher.getPublisherId());

            return preparedStatement.executeUpdate()>0;

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    @Override
    public boolean delete(Integer id) {

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                PublisherQueries.DELETE
                        )

        ) {

            preparedStatement.setInt(1,id);

            return preparedStatement.executeUpdate()>0;

        }catch (SQLException e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage(),
                    "SQL Error",
                    JOptionPane.ERROR_MESSAGE
            );

            throw new RuntimeException(e);

        }

    }

}