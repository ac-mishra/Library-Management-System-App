package com.librarymanagement.common.constants;

/**
 * SQL Queries for Publisher.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
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
            WHERE publisher_id = ?
            """;

}