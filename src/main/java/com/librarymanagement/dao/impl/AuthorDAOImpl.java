package com.librarymanagement.dao.impl;

import com.librarymanagement.common.constants.AuthorQueries;
import com.librarymanagement.config.DBConnection;
import com.librarymanagement.entity.Author;
import com.librarymanagement.mapper.AuthorRowMapper;
import com.librarymanagement.repository.AuthorRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthorDAOImpl
        implements AuthorRepository {

    @Override
    public List<Author> findAll() {

        List<Author> authors =
                new ArrayList<>();

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                AuthorQueries.FIND_ALL
                        );

                ResultSet resultSet =
                        preparedStatement.executeQuery()

        ) {

            while (resultSet.next()) {

                authors.add(
                        AuthorRowMapper.map(resultSet)
                );

            }

        }

        catch (SQLException exception) {

            throw new RuntimeException(exception);

        }

        return authors;

    }

    @Override
    public Optional<Author> findById(Integer id) {

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                AuthorQueries.FIND_BY_ID
                        )

        ) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet =
                         preparedStatement.executeQuery()) {

                if (resultSet.next()) {

                    return Optional.of(
                            AuthorRowMapper.map(resultSet)
                    );

                }

            }

        }

        catch (SQLException exception) {

            throw new RuntimeException(exception);

        }

        return Optional.empty();

    }

    @Override
    public Author save(Author author) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(Author author) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException();
    }

}