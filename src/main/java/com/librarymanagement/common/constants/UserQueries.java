package com.librarymanagement.common.constants;

/**
 * SQL queries related to User operations.
 *
 * <p>This utility class contains all SQL statements used by the
 * User repository implementation.</p>
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public final class UserQueries {

    private UserQueries() {
        throw new UnsupportedOperationException(
                "Utility class cannot be instantiated."
        );
    }

    /**
     * Insert a new user.
     */
    public static final String INSERT_USER = """
            INSERT INTO users
            (
                role_id,
                full_name,
                username,
                password,
                email,
                phone,
                is_active
            )
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

    /**
     * Update an existing user.
     */
    public static final String UPDATE_USER = """
            UPDATE users
            SET
                role_id = ?,
                full_name = ?,
                username = ?,
                password = ?,
                email = ?,
                phone = ?,
                is_active = ?
            WHERE user_id = ?
            """;

    /**
     * Delete user by ID.
     */
    public static final String DELETE_USER = """
            DELETE FROM users
            WHERE user_id = ?
            """;

    /**
     * Find user by ID.
     */
    public static final String FIND_BY_ID = """
            SELECT
                u.*,
                r.role_name,
                r.description
            FROM users u
            INNER JOIN roles r
                ON u.role_id = r.role_id
            WHERE u.user_id = ?
            """;

    /**
     * Find user by username.
     */
    public static final String FIND_BY_USERNAME = """
            SELECT
                u.*,
                r.role_name,
                r.description
            FROM users u
            INNER JOIN roles r
                ON u.role_id = r.role_id
            WHERE u.username = ?
            """;

    /**
     * Find user by email.
     */
    public static final String FIND_BY_EMAIL = """
            SELECT
                u.*,
                r.role_name,
                r.description
            FROM users u
            INNER JOIN roles r
                ON u.role_id = r.role_id
            WHERE u.email = ?
            """;

    /**
     * Retrieve all users.
     */
    public static final String FIND_ALL = """
            SELECT
                u.*,
                r.role_name,
                r.description
            FROM users u
            INNER JOIN roles r
                ON u.role_id = r.role_id
            ORDER BY u.full_name
            """;

    /**
     * Check if username already exists.
     */
    public static final String EXISTS_BY_USERNAME = """
            SELECT COUNT(*)
            FROM users
            WHERE username = ?
            """;

    /**
     * Check if email already exists.
     */
    public static final String EXISTS_BY_EMAIL = """
            SELECT COUNT(*)
            FROM users
            WHERE email = ?
            """;

}