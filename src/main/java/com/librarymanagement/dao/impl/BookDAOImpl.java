package com.librarymanagement.dao.impl;

import com.librarymanagement.common.constants.BookQueries;
import com.librarymanagement.config.DBConnection;
import com.librarymanagement.entity.Book;
import com.librarymanagement.mapper.BookRowMapper;
import com.librarymanagement.repository.BookRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Book Repository Implementation.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public class BookDAOImpl implements BookRepository {

    private static final Logger LOGGER =
            LogManager.getLogger(BookDAOImpl.class);

    public BookDAOImpl() {
    }

    /**
     * Save Book.
     */
    @Override
    public Book save(Book book) {

        Objects.requireNonNull(
                book,
                "Book cannot be null."
        );

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                BookQueries.INSERT_BOOK,
                                Statement.RETURN_GENERATED_KEYS
                        )

        ) {

            preparedStatement.setInt(
                    1,
                    book.getCategory().getCategoryId()
            );

            preparedStatement.setInt(
                    2,
                    book.getAuthor().getAuthorId()
            );

            preparedStatement.setInt(
                    3,
                    book.getPublisher().getPublisherId()
            );

            preparedStatement.setString(
                    4,
                    book.getIsbn()
            );

            preparedStatement.setString(
                    5,
                    book.getTitle()
            );

            preparedStatement.setString(
                    6,
                    book.getEdition()
            );

            preparedStatement.setString(
                    7,
                    book.getLanguage()
            );

            preparedStatement.setInt(
                    8,
                    book.getPublishYear()
            );

            preparedStatement.setInt(
                    9,
                    book.getTotalPages()
            );

            preparedStatement.setString(
                    10,
                    book.getDescription()
            );

            int rows =
                    preparedStatement.executeUpdate();

            if (rows == 0) {

                throw new RuntimeException(
                        "Unable to save book."
                );

            }

            try (

                    ResultSet generatedKeys =
                            preparedStatement.getGeneratedKeys()

            ) {

                if (generatedKeys.next()) {

                    book.setBookId(
                            generatedKeys.getInt(1)
                    );

                }

            }

            LOGGER.info(
                    "Book saved successfully : {}",
                    book.getTitle()
            );

            return book;

        }

        catch (SQLException exception) {

            LOGGER.error(
                    "Error while saving book.",
                    exception
            );

            throw new RuntimeException(
                    "Unable to save book.",
                    exception
            );

        }

    }

    /**
     * Update Book.
     */
    @Override
    public boolean update(Book book) {

        Objects.requireNonNull(
                book,
                "Book cannot be null."
        );

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                BookQueries.UPDATE_BOOK
                        )

        ) {

            preparedStatement.setInt(
                    1,
                    book.getCategory().getCategoryId()
            );

            preparedStatement.setInt(
                    2,
                    book.getAuthor().getAuthorId()
            );

            preparedStatement.setInt(
                    3,
                    book.getPublisher().getPublisherId()
            );

            preparedStatement.setString(
                    4,
                    book.getIsbn()
            );

            preparedStatement.setString(
                    5,
                    book.getTitle()
            );

            preparedStatement.setString(
                    6,
                    book.getEdition()
            );

            preparedStatement.setString(
                    7,
                    book.getLanguage()
            );

            preparedStatement.setInt(
                    8,
                    book.getPublishYear()
            );

            preparedStatement.setInt(
                    9,
                    book.getTotalPages()
            );

            preparedStatement.setString(
                    10,
                    book.getDescription()
            );

            preparedStatement.setInt(
                    11,
                    book.getBookId()
            );

            int rows =
                    preparedStatement.executeUpdate();

            LOGGER.info(
                    "Book updated successfully : {}",
                    book.getTitle()
            );

            return rows > 0;

        }

        catch (SQLException exception) {

            LOGGER.error(
                    "Error while updating book.",
                    exception
            );

            throw new RuntimeException(
                    "Unable to update book.",
                    exception
            );

        }

    }

    /**
     * Delete Book.
     */
    @Override
    public boolean delete(Integer bookId) {

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                BookQueries.DELETE_BOOK
                        )

        ) {

            preparedStatement.setInt(
                    1,
                    bookId
            );

            int rows =
                    preparedStatement.executeUpdate();

            LOGGER.info(
                    "Book deleted successfully : {}",
                    bookId
            );

            return rows > 0;

        }

        catch (SQLException exception) {

            LOGGER.error(
                    "Error while deleting book.",
                    exception
            );

            throw new RuntimeException(
                    "Unable to delete book.",
                    exception
            );

        }

    }

    /**
     * Find Book by ID.
     */
    @Override
    public Optional<Book> findById(Integer bookId) {

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                BookQueries.FIND_BY_ID
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

                if (resultSet.next()) {

                    return Optional.of(
                            BookRowMapper.map(resultSet)
                    );

                }

            }

        }

        catch (SQLException exception) {

            LOGGER.error(
                    "Error while finding book by ID.",
                    exception
            );

            throw new RuntimeException(
                    "Unable to find book.",
                    exception
            );

        }

        return Optional.empty();

    }

    /**
     * Find Book by ISBN.
     */
    @Override
    public Optional<Book> findByIsbn(String isbn) {

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                BookQueries.FIND_BY_ISBN
                        )

        ) {

            preparedStatement.setString(
                    1,
                    isbn
            );

            try (

                    ResultSet resultSet =
                            preparedStatement.executeQuery()

            ) {

                if (resultSet.next()) {

                    return Optional.of(
                            BookRowMapper.map(resultSet)
                    );

                }

            }

        }

        catch (SQLException exception) {

            LOGGER.error(
                    "Error while finding book by ISBN.",
                    exception
            );

            throw new RuntimeException(
                    "Unable to find book.",
                    exception
            );

        }

        return Optional.empty();

    }

    /**
     * Find All Books.
     */
    @Override
    public List<Book> findAll() {

        List<Book> books =
                new ArrayList<>();

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                BookQueries.FIND_ALL
                        );

                ResultSet resultSet =
                        preparedStatement.executeQuery()

        ) {

            while (resultSet.next()) {

                books.add(
                        BookRowMapper.map(resultSet)
                );

            }

        }

        catch (SQLException exception) {

            LOGGER.error(
                    "Error while fetching books.",
                    exception
            );

            throw new RuntimeException(
                    "Unable to fetch books.",
                    exception
            );

        }

        return books;

    }

    /**
     * Search books by title.
     */
    @Override
    public List<Book> findByTitle(String title) {

        List<Book> books = new ArrayList<>();

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                BookQueries.SEARCH_BY_TITLE
                        )

        ) {

            preparedStatement.setString(
                    1,
                    "%" + title + "%"
            );

            try (

                    ResultSet resultSet =
                            preparedStatement.executeQuery()

            ) {

                while (resultSet.next()) {

                    books.add(
                            BookRowMapper.map(resultSet)
                    );

                }

            }

        }

        catch (SQLException exception) {

            LOGGER.error(
                    "Error while searching books by title.",
                    exception
            );

            throw new RuntimeException(
                    "Unable to search books.",
                    exception
            );

        }

        return books;

    }

    /**
     * Find books by author.
     */
    @Override
    public List<Book> findByAuthor(Integer authorId) {

        List<Book> books = new ArrayList<>();

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                BookQueries.SEARCH_BY_AUTHOR
                        )

        ) {

            preparedStatement.setInt(
                    1,
                    authorId
            );

            try (

                    ResultSet resultSet =
                            preparedStatement.executeQuery()

            ) {

                while (resultSet.next()) {

                    books.add(
                            BookRowMapper.map(resultSet)
                    );

                }

            }

        }

        catch (SQLException exception) {

            LOGGER.error(
                    "Error while finding books by author.",
                    exception
            );

            throw new RuntimeException(
                    "Unable to fetch books.",
                    exception
            );

        }

        return books;

    }

    /**
     * Find books by category.
     */
    @Override
    public List<Book> findByCategory(Integer categoryId) {

        List<Book> books = new ArrayList<>();

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                BookQueries.SEARCH_BY_CATEGORY
                        )

        ) {

            preparedStatement.setInt(
                    1,
                    categoryId
            );

            try (

                    ResultSet resultSet =
                            preparedStatement.executeQuery()

            ) {

                while (resultSet.next()) {

                    books.add(
                            BookRowMapper.map(resultSet)
                    );

                }

            }

        }

        catch (SQLException exception) {

            LOGGER.error(
                    "Error while finding books by category.",
                    exception
            );

            throw new RuntimeException(
                    "Unable to fetch books.",
                    exception
            );

        }

        return books;

    }

    /**
     * Check if ISBN already exists.
     */
    @Override
    public boolean existsByIsbn(String isbn) {

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                BookQueries.EXISTS_BY_ISBN
                        )

        ) {

            preparedStatement.setString(
                    1,
                    isbn
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

            LOGGER.error(
                    "Error while checking ISBN.",
                    exception
            );

            throw new RuntimeException(
                    "Unable to verify ISBN.",
                    exception
            );

        }

        return false;

    }

}