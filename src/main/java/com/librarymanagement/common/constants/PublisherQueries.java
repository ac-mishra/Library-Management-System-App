package com.librarymanagement.common.constants;

public final class PublisherQueries {

    private PublisherQueries() {
    }

    public static final String FIND_ALL = """
            SELECT *
            FROM publishers
            ORDER BY publisher_name
            """;

    public static final String FIND_BY_ID = """
            SELECT *
            FROM publishers
            WHERE publisher_id=?
            """;

    public static final String INSERT = """
            INSERT INTO publishers
            (
                publisher_name,
                email,
                phone,
                address,
                website
            )
            VALUES
            (
                ?,?,?,?,?
            )
            """;

    public static final String UPDATE = """
            UPDATE publishers
            SET
                publisher_name=?,
                email=?,
                phone=?,
                address=?,
                website=?
            WHERE publisher_id=?
            """;

    public static final String DELETE = """
            DELETE FROM publishers
            WHERE publisher_id=?
            """;
}