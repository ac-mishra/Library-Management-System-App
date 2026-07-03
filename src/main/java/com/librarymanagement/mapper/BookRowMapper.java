package com.librarymanagement.mapper;

import com.librarymanagement.entity.Author;
import com.librarymanagement.entity.Book;
import com.librarymanagement.entity.Category;
import com.librarymanagement.entity.Publisher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Maps ResultSet to Book object.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public final class BookRowMapper {

    private BookRowMapper() {
    }

    /**
     * Maps a database row to Book.
     *
     * @param resultSet database result
     * @return Book object
     * @throws SQLException if mapping fails
     */
    public static Book map(ResultSet resultSet)
            throws SQLException {

        Book book = new Book();

        book.setBookId(
                resultSet.getInt("book_id")
        );

        book.setIsbn(
                resultSet.getString("isbn")
        );

        book.setTitle(
                resultSet.getString("title")
        );

        book.setEdition(
                resultSet.getString("edition")
        );

        book.setLanguage(
                resultSet.getString("language")
        );

        book.setPublishYear(
                resultSet.getInt("publish_year")
        );

        book.setTotalPages(
                resultSet.getInt("total_pages")
        );

        book.setDescription(
                resultSet.getString("description")
        );

        Timestamp created =
                resultSet.getTimestamp("created_at");

        if (created != null) {

            book.setCreatedAt(
                    created.toLocalDateTime()
            );

        }

        Timestamp updated =
                resultSet.getTimestamp("updated_at");

        if (updated != null) {

            book.setUpdatedAt(
                    updated.toLocalDateTime()
            );

        }

        Category category =
                new Category();

        category.setCategoryId(
                resultSet.getInt("category_id")
        );

        category.setCategoryName(
                resultSet.getString("category_name")
        );

        book.setCategory(category);

        Author author =
                new Author();

        author.setAuthorId(
                resultSet.getInt("author_id")
        );

        author.setFirstName(
                resultSet.getString("first_name")
        );

        author.setLastName(
                resultSet.getString("last_name")
        );

        book.setAuthor(author);

        Publisher publisher =
                new Publisher();

        publisher.setPublisherId(
                resultSet.getInt("publisher_id")
        );

        publisher.setPublisherName(
                resultSet.getString("publisher_name")
        );

        book.setPublisher(publisher);

        return book;

    }

}