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

        try (
                Connection connection = DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                AuthorQueries.INSERT,
                                PreparedStatement.RETURN_GENERATED_KEYS
                        )
        ) {

            preparedStatement.setString(1, author.getFirstName());
            preparedStatement.setString(2, author.getLastName());
            preparedStatement.setString(3, author.getEmail());
            preparedStatement.setString(4, author.getPhone());
            preparedStatement.setString(5, author.getCountry());
            preparedStatement.setString(6, author.getBiography());

            preparedStatement.executeUpdate();

            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {

                if (rs.next()) {

                    author.setAuthorId(rs.getInt(1));

                }

            }

            return author;

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    @Override
    public boolean update(Author author) {

        try (
                Connection connection = DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                AuthorQueries.UPDATE
                        )
        ) {

            preparedStatement.setString(1, author.getFirstName());
            preparedStatement.setString(2, author.getLastName());
            preparedStatement.setString(3, author.getEmail());
            preparedStatement.setString(4, author.getPhone());
            preparedStatement.setString(5, author.getCountry());
            preparedStatement.setString(6, author.getBiography());
            preparedStatement.setInt(7, author.getAuthorId());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }
    @Override
    public boolean delete(Integer id) {

        try (
                Connection connection = DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                AuthorQueries.DELETE
                        )
        ) {

            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }
}