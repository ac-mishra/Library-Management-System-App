package com.librarymanagement.common.constants;

public final class CategoryQueries {

    private CategoryQueries() {
    }

    public static final String FIND_ALL = """
            SELECT *
            FROM categories
            ORDER BY category_name
            """;

    public static final String FIND_BY_ID = """
            SELECT *
            FROM categories
            WHERE category_id=?
            """;

    public static final String FIND_BY_NAME = """
            SELECT *
            FROM categories
            WHERE category_name=?
            """;

    public static final String INSERT = """
            INSERT INTO categories
            (
                category_name,
                description
            )
            VALUES
            (
                ?,?
            )
            """;

    public static final String UPDATE = """
            UPDATE categories
            SET
                category_name=?,
                description=?
            WHERE category_id=?
            """;

    public static final String DELETE = """
            DELETE FROM categories
            WHERE category_id=?
            """;
}