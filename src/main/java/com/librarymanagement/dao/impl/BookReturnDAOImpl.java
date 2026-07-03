package com.librarymanagement.dao.impl;

import com.librarymanagement.common.constants.BookReturnQueries;
import com.librarymanagement.config.DBConnection;
import com.librarymanagement.entity.BookReturn;
import com.librarymanagement.mapper.BookReturnRowMapper;
import com.librarymanagement.repository.BookReturnRepository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * DAO Implementation for Book Return.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public class BookReturnDAOImpl
        implements BookReturnRepository {

    @Override
    public BookReturn save(BookReturn bookReturn) {

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                BookReturnQueries.INSERT,
                                Statement.RETURN_GENERATED_KEYS
                        )

        ) {

            preparedStatement.setInt(
                    1,
                    bookReturn.getBookIssue().getIssueId()
            );

            preparedStatement.setInt(
                    2,
                    bookReturn.getReceivedBy().getUserId()
            );

            preparedStatement.setDate(
                    3,
                    Date.valueOf(
                            bookReturn.getReturnDate()
                    )
            );

            preparedStatement.setDouble(
                    4,
                    bookReturn.getFineAmount()
            );

            preparedStatement.setString(
                    5,
                    bookReturn.getRemarks()
            );

            int rows =
                    preparedStatement.executeUpdate();

            if (rows > 0) {

                ResultSet generatedKeys =
                        preparedStatement.getGeneratedKeys();

                if (generatedKeys.next()) {

                    bookReturn.setReturnId(
                            generatedKeys.getInt(1)
                    );

                }

            }

            return bookReturn;

        }

        catch (SQLException exception) {

            throw new RuntimeException(exception);

        }

    }

    @Override
    public boolean update(BookReturn bookReturn) {

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                BookReturnQueries.UPDATE
                        )

        ) {

            preparedStatement.setInt(
                    1,
                    bookReturn.getBookIssue().getIssueId()
            );

            preparedStatement.setInt(
                    2,
                    bookReturn.getReceivedBy().getUserId()
            );

            preparedStatement.setDate(
                    3,
                    Date.valueOf(
                            bookReturn.getReturnDate()
                    )
            );

            preparedStatement.setDouble(
                    4,
                    bookReturn.getFineAmount()
            );

            preparedStatement.setString(
                    5,
                    bookReturn.getRemarks()
            );

            preparedStatement.setInt(
                    6,
                    bookReturn.getReturnId()
            );

            return preparedStatement.executeUpdate() > 0;

        }

        catch (SQLException exception) {

            throw new RuntimeException(exception);

        }

    }

    @Override
    public boolean delete(Integer returnId) {

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                BookReturnQueries.DELETE
                        )

        ) {

            preparedStatement.setInt(
                    1,
                    returnId
            );

            return preparedStatement.executeUpdate() > 0;

        }

        catch (SQLException exception) {

            throw new RuntimeException(exception);

        }

    }
    @Override
    public Optional<BookReturn> findById(Integer returnId) {

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                BookReturnQueries.FIND_BY_ID
                        )

        ) {

            preparedStatement.setInt(
                    1,
                    returnId
            );

            try (

                    ResultSet resultSet =
                            preparedStatement.executeQuery()

            ) {

                if (resultSet.next()) {

                    return Optional.of(
                            BookReturnRowMapper.map(resultSet)
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
    public List<BookReturn> findAll() {

        List<BookReturn> returns =
                new ArrayList<>();

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                BookReturnQueries.FIND_ALL
                        );

                ResultSet resultSet =
                        preparedStatement.executeQuery()

        ) {

            while (resultSet.next()) {

                returns.add(
                        BookReturnRowMapper.map(resultSet)
                );

            }

        }

        catch (SQLException exception) {

            throw new RuntimeException(exception);

        }

        return returns;

    }

    @Override
    public Optional<BookReturn> findByIssueId(Integer issueId) {

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                BookReturnQueries.FIND_BY_ISSUE
                        )

        ) {

            preparedStatement.setInt(
                    1,
                    issueId
            );

            try (

                    ResultSet resultSet =
                            preparedStatement.executeQuery()

            ) {

                if (resultSet.next()) {

                    return Optional.of(
                            BookReturnRowMapper.map(resultSet)
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
    public boolean existsByIssueId(Integer issueId) {

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                BookReturnQueries.EXISTS_BY_ISSUE
                        )

        ) {

            preparedStatement.setInt(
                    1,
                    issueId
            );

            try (

                    ResultSet resultSet =
                            preparedStatement.executeQuery()

            ) {

                if (resultSet.next()) {

                    return resultSet.getInt(1) > 0;

                }

            }

        }

        catch (SQLException exception) {

            throw new RuntimeException(exception);

        }

        return false;

    }

    @Override
    public List<BookReturn> findAllReturns() {

        return findAll();

    }

}