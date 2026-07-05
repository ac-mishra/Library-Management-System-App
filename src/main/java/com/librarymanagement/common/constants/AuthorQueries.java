package com.librarymanagement.common.constants;

public final class AuthorQueries {

    private AuthorQueries() {
    }

    public static final String FIND_ALL = """
            SELECT *
            FROM authors
            ORDER BY first_name,last_name
            """;

    public static final String FIND_BY_ID = """
            SELECT *
            FROM authors
            WHERE author_id=?
            """;

    public static final String INSERT = """
            INSERT INTO authors
            (
                first_name,
                last_name,
                email,
                phone,
                country,
                biography
            )
            VALUES
            (
                ?,?,?,?,?,?
            )
            """;

    public static final String UPDATE = """
            UPDATE authors
            SET
                first_name=?,
                last_name=?,
                email=?,
                phone=?,
                country=?,
                biography=?
            WHERE author_id=?
            """;

    public static final String DELETE = """
            DELETE FROM authors
            WHERE author_id=?
            """;
}