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

}