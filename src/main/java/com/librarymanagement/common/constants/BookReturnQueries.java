package com.librarymanagement.common.constants;

/**
 * SQL Queries for Book Return.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public final class BookReturnQueries {

    private BookReturnQueries() {
    }

    public static final String INSERT = """
            INSERT INTO book_returns
            (
                issue_id,
                received_by,
                return_date,
                fine_amount,
                remarks
            )
            VALUES
            (?,?,?,?,?)
            """;

    public static final String UPDATE = """
            UPDATE book_returns
            SET
                issue_id=?,
                received_by=?,
                return_date=?,
                fine_amount=?,
                remarks=?
            WHERE return_id=?
            """;

    public static final String DELETE = """
            DELETE
            FROM book_returns
            WHERE return_id=?
            """;

    public static final String FIND_BY_ID = """
            SELECT *
            FROM book_returns
            WHERE return_id=?
            """;

    public static final String FIND_ALL = """
            SELECT *
            FROM book_returns
            ORDER BY return_date DESC
            """;

    public static final String FIND_BY_ISSUE = """
            SELECT *
            FROM book_returns
            WHERE issue_id=?
            """;

    public static final String EXISTS_BY_ISSUE = """
            SELECT COUNT(*)
            FROM book_returns
            WHERE issue_id=?
            """;

}