package com.librarymanagement.common.constants;

/**
 * SQL queries for Book module.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public final class BookQueries {

    private BookQueries() {
    }

    public static final String INSERT_BOOK = """
        INSERT INTO books
        (
            category_id,
            author_id,
            publisher_id,
            isbn,
            title,
            edition,
            language,
            publish_year,
            total_pages,
            description
        )
        VALUES
        (?,?,?,?,?,?,?,?,?,?)
        """;

    public static final String UPDATE_BOOK = """
        UPDATE books
        SET
            category_id=?,
            author_id=?,
            publisher_id=?,
            isbn=?,
            title=?,
            edition=?,
            language=?,
            publish_year=?,
            total_pages=?,
            description=?
        WHERE book_id=?
        """;

    public static final String DELETE_BOOK = """
        DELETE FROM books
        WHERE book_id=?
        """;

    public static final String FIND_BY_ID = """
        SELECT
            b.*,

            c.category_name,

            a.first_name,
            a.last_name,

            p.publisher_name

        FROM books b

        INNER JOIN categories c
            ON b.category_id=c.category_id

        INNER JOIN authors a
            ON b.author_id=a.author_id

        INNER JOIN publishers p
            ON b.publisher_id=p.publisher_id

        WHERE b.book_id=?
        """;

    public static final String FIND_ALL = """
        SELECT
            b.*,

            c.category_name,

            a.first_name,
            a.last_name,

            p.publisher_name

        FROM books b

        INNER JOIN categories c
            ON b.category_id=c.category_id

        INNER JOIN authors a
            ON b.author_id=a.author_id

        INNER JOIN publishers p
            ON b.publisher_id=p.publisher_id

        ORDER BY b.title
        """;

    public static final String FIND_BY_ISBN = """
        SELECT
            b.*,

            c.category_name,

            a.first_name,
            a.last_name,

            p.publisher_name

        FROM books b

        INNER JOIN categories c
            ON b.category_id=c.category_id

        INNER JOIN authors a
            ON b.author_id=a.author_id

        INNER JOIN publishers p
            ON b.publisher_id=p.publisher_id

        WHERE b.isbn=?
        """;

    public static final String SEARCH_BY_TITLE = """
        SELECT
            b.*,

            c.category_name,

            a.first_name,
            a.last_name,

            p.publisher_name

        FROM books b

        INNER JOIN categories c
            ON b.category_id=c.category_id

        INNER JOIN authors a
            ON b.author_id=a.author_id

        INNER JOIN publishers p
            ON b.publisher_id=p.publisher_id

        WHERE b.title LIKE ?

        ORDER BY b.title
        """;

    public static final String EXISTS_BY_ISBN = """
        SELECT COUNT(*)
        FROM books
        WHERE isbn=?
        """;

    public static final String SEARCH_BY_AUTHOR = """
        SELECT
            b.*,

            c.category_name,

            a.first_name,
            a.last_name,

            p.publisher_name

        FROM books b

        INNER JOIN categories c
            ON b.category_id=c.category_id

        INNER JOIN authors a
            ON b.author_id=a.author_id

        INNER JOIN publishers p
            ON b.publisher_id=p.publisher_id

        WHERE b.author_id=?
        """;

    public static final String SEARCH_BY_CATEGORY = """
        SELECT
            b.*,
            c.category_name,
            a.first_name,
            a.last_name,
            p.publisher_name
        FROM books b
        INNER JOIN categories c
            ON b.category_id = c.category_id
        INNER JOIN authors a
            ON b.author_id = a.author_id
        INNER JOIN publishers p
            ON b.publisher_id = p.publisher_id
        WHERE b.category_id = ?
        ORDER BY b.title
        """;

}