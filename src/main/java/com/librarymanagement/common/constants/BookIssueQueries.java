package com.librarymanagement.common.constants;

/**
 * SQL Queries for Book Issue.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public final class BookIssueQueries {

    private BookIssueQueries() {
    }

    public static final String INSERT = """
            INSERT INTO book_issues
            (
                book_id,
                member_id,
                issued_by,
                issue_date,
                due_date,
                return_date,
                fine_amount,
                status
            )
            VALUES
            (?,?,?,?,?,?,?,?)
            """;

    public static final String UPDATE = """
            UPDATE book_issues
            SET
                book_id=?,
                member_id=?,
                issued_by=?,
                issue_date=?,
                due_date=?,
                return_date=?,
                fine_amount=?,
                status=?
            WHERE issue_id=?
            """;

    public static final String DELETE = """
            DELETE FROM book_issues
            WHERE issue_id=?
            """;

    public static final String FIND_BY_ID = """
            SELECT *
            FROM book_issues
            WHERE issue_id=?
            """;

    public static final String FIND_ALL = """
            SELECT *
            FROM book_issues
            ORDER BY issue_date DESC
            """;

    public static final String FIND_ACTIVE = """
            SELECT *
            FROM book_issues
            WHERE status='ISSUED'
            ORDER BY issue_date DESC
            """;

    public static final String FIND_BY_MEMBER = """
            SELECT *
            FROM book_issues
            WHERE member_id=?
            ORDER BY issue_date DESC
            """;

    public static final String FIND_BY_BOOK = """
            SELECT *
            FROM book_issues
            WHERE book_id=?
            ORDER BY issue_date DESC
            """;

    public static final String FIND_ACTIVE_ISSUE = """
            SELECT *
            FROM book_issues
            WHERE member_id=?
            AND book_id=?
            AND status='ISSUED'
            """;

}