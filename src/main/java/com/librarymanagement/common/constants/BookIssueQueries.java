package com.librarymanagement.common.constants;

/**
 * SQL Queries for Book Issue.
 * @author Amrit Chandan Mishra
 * @version 2.0.0
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
            (
                ?, ?, ?, ?, ?, ?, ?, ?
            )
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
            SELECT
                bi.*,
                b.title,
                b.isbn,
                m.first_name,
                m.last_name
            FROM book_issues bi
            INNER JOIN books b
                    ON bi.book_id = b.book_id
            INNER JOIN members m
                    ON bi.member_id = m.member_id
            WHERE bi.issue_id=?
            """;

    public static final String FIND_ALL = """
            SELECT
                bi.*,
                b.title,
                b.isbn,
                m.first_name,
                m.last_name
            FROM book_issues bi
            INNER JOIN books b
                    ON bi.book_id = b.book_id
            INNER JOIN members m
                    ON bi.member_id = m.member_id
            ORDER BY bi.issue_date DESC
            """;

    public static final String FIND_ACTIVE = """
            SELECT
                bi.*,
                b.title,
                b.isbn,
                m.first_name,
                m.last_name
            FROM book_issues bi
            INNER JOIN books b
                    ON bi.book_id = b.book_id
            INNER JOIN members m
                    ON bi.member_id = m.member_id
            WHERE bi.status='ISSUED'
            ORDER BY bi.issue_date DESC
            """;

    public static final String FIND_BY_MEMBER = """
            SELECT
                bi.*,
                b.title,
                b.isbn,
                m.first_name,
                m.last_name
            FROM book_issues bi
            INNER JOIN books b
                    ON bi.book_id = b.book_id
            INNER JOIN members m
                    ON bi.member_id = m.member_id
            WHERE bi.member_id=?
            ORDER BY bi.issue_date DESC
            """;

    public static final String FIND_BY_BOOK = """
            SELECT
                bi.*,
                b.title,
                b.isbn,
                m.first_name,
                m.last_name
            FROM book_issues bi
            INNER JOIN books b
                    ON bi.book_id = b.book_id
            INNER JOIN members m
                    ON bi.member_id = m.member_id
            WHERE bi.book_id=?
            ORDER BY bi.issue_date DESC
            """;

    public static final String FIND_ACTIVE_ISSUE = """
            SELECT
                bi.*,
                b.title,
                b.isbn,
                m.first_name,
                m.last_name
            FROM book_issues bi
            INNER JOIN books b
                    ON bi.book_id = b.book_id
            INNER JOIN members m
                    ON bi.member_id = m.member_id
            WHERE bi.member_id=?
              AND bi.book_id=?
              AND bi.status='ISSUED'
            LIMIT 1
            """;

}