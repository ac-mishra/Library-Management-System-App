package com.librarymanagement.dao.impl;

import com.librarymanagement.common.constants.BookIssueQueries;
import com.librarymanagement.config.DBConnection;
import com.librarymanagement.entity.BookIssue;
import com.librarymanagement.mapper.BookIssueRowMapper;
import com.librarymanagement.repository.BookIssueRepository;

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
 * DAO Implementation for Book Issue.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public class BookIssueDAOImpl
        implements BookIssueRepository {

    @Override
    public BookIssue save(BookIssue issue) {

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                BookIssueQueries.INSERT,
                                Statement.RETURN_GENERATED_KEYS
                        )

        ) {

            preparedStatement.setInt(
                    1,
                    issue.getBook().getBookId()
            );

            preparedStatement.setInt(
                    2,
                    issue.getMember().getMemberId()
            );

            preparedStatement.setInt(
                    3,
                    issue.getIssuedBy().getUserId()
            );

            preparedStatement.setDate(
                    4,
                    Date.valueOf(
                            issue.getIssueDate()
                    )
            );

            preparedStatement.setDate(
                    5,
                    Date.valueOf(
                            issue.getDueDate()
                    )
            );

            if (issue.getReturnDate() == null) {

                preparedStatement.setNull(
                        6,
                        java.sql.Types.DATE
                );

            } else {

                preparedStatement.setDate(
                        6,
                        Date.valueOf(
                                issue.getReturnDate()
                        )
                );

            }

            preparedStatement.setDouble(
                    7,
                    issue.getFineAmount()
            );

            preparedStatement.setString(
                    8,
                    issue.getStatus()
            );

            int rows =
                    preparedStatement.executeUpdate();

            if (rows > 0) {

                ResultSet generatedKeys =
                        preparedStatement.getGeneratedKeys();

                if (generatedKeys.next()) {

                    issue.setIssueId(
                            generatedKeys.getInt(1)
                    );

                }

            }

            return issue;

        }

        catch (SQLException exception) {

            throw new RuntimeException(exception);

        }

    }

    @Override
    public boolean update(BookIssue issue) {

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                BookIssueQueries.UPDATE
                        )

        ) {

            preparedStatement.setInt(
                    1,
                    issue.getBook().getBookId()
            );

            preparedStatement.setInt(
                    2,
                    issue.getMember().getMemberId()
            );

            preparedStatement.setInt(
                    3,
                    issue.getIssuedBy().getUserId()
            );

            preparedStatement.setDate(
                    4,
                    Date.valueOf(
                            issue.getIssueDate()
                    )
            );

            preparedStatement.setDate(
                    5,
                    Date.valueOf(
                            issue.getDueDate()
                    )
            );

            if (issue.getReturnDate() == null) {

                preparedStatement.setNull(
                        6,
                        java.sql.Types.DATE
                );

            } else {

                preparedStatement.setDate(
                        6,
                        Date.valueOf(
                                issue.getReturnDate()
                        )
                );

            }

            preparedStatement.setDouble(
                    7,
                    issue.getFineAmount()
            );

            preparedStatement.setString(
                    8,
                    issue.getStatus()
            );

            preparedStatement.setInt(
                    9,
                    issue.getIssueId()
            );

            return preparedStatement.executeUpdate() > 0;

        }

        catch (SQLException exception) {

            throw new RuntimeException(exception);

        }

    }

    @Override
    public boolean delete(Integer issueId) {

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                BookIssueQueries.DELETE
                        )

        ) {

            preparedStatement.setInt(
                    1,
                    issueId
            );

            return preparedStatement.executeUpdate() > 0;

        }

        catch (SQLException exception) {

            throw new RuntimeException(exception);

        }

    }

    @Override
    public Optional<BookIssue> findById(Integer issueId) {

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                BookIssueQueries.FIND_BY_ID
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
                            BookIssueRowMapper.map(resultSet)
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
    public List<BookIssue> findAll() {

        List<BookIssue> issues =
                new ArrayList<>();

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                BookIssueQueries.FIND_ALL
                        );

                ResultSet resultSet =
                        preparedStatement.executeQuery()

        ) {

            while (resultSet.next()) {

                issues.add(
                        BookIssueRowMapper.map(resultSet)
                );

            }

        }

        catch (SQLException exception) {

            throw new RuntimeException(exception);

        }

        return issues;

    }
    @Override
    public List<BookIssue> findActiveIssues() {

        List<BookIssue> issues =
                new ArrayList<>();

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                BookIssueQueries.FIND_ACTIVE
                        );

                ResultSet resultSet =
                        preparedStatement.executeQuery()

        ) {

            while (resultSet.next()) {

                issues.add(
                        BookIssueRowMapper.map(resultSet)
                );

            }

        }

        catch (SQLException exception) {

            throw new RuntimeException(exception);

        }

        return issues;

    }

    @Override
    public List<BookIssue> findByMemberId(Integer memberId) {

        List<BookIssue> issues =
                new ArrayList<>();

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                BookIssueQueries.FIND_BY_MEMBER
                        )

        ) {

            preparedStatement.setInt(
                    1,
                    memberId
            );

            try (

                    ResultSet resultSet =
                            preparedStatement.executeQuery()

            ) {

                while (resultSet.next()) {

                    issues.add(
                            BookIssueRowMapper.map(resultSet)
                    );

                }

            }

        }

        catch (SQLException exception) {

            throw new RuntimeException(exception);

        }

        return issues;

    }

    @Override
    public List<BookIssue> findByBookId(Integer bookId) {

        List<BookIssue> issues =
                new ArrayList<>();

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                BookIssueQueries.FIND_BY_BOOK
                        )

        ) {

            preparedStatement.setInt(
                    1,
                    bookId
            );

            try (

                    ResultSet resultSet =
                            preparedStatement.executeQuery()

            ) {

                while (resultSet.next()) {

                    issues.add(
                            BookIssueRowMapper.map(resultSet)
                    );

                }

            }

        }

        catch (SQLException exception) {

            throw new RuntimeException(exception);

        }

        return issues;

    }

    @Override
    public Optional<BookIssue> findActiveIssue(
            Integer memberId,
            Integer bookId) {

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                BookIssueQueries.FIND_ACTIVE_ISSUE
                        )

        ) {

            preparedStatement.setInt(
                    1,
                    memberId
            );

            preparedStatement.setInt(
                    2,
                    bookId
            );

            try (

                    ResultSet resultSet =
                            preparedStatement.executeQuery()

            ) {

                if (resultSet.next()) {

                    return Optional.of(
                            BookIssueRowMapper.map(resultSet)
                    );

                }

            }

        }

        catch (SQLException exception) {

            throw new RuntimeException(exception);

        }

        return Optional.empty();

    }

}