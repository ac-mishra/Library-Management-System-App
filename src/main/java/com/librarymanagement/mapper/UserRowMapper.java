package com.librarymanagement.mapper;

import com.librarymanagement.entity.Role;
import com.librarymanagement.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.sql.Timestamp;

/**
 * Maps a ResultSet row to a User object.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public final class UserRowMapper {

    private UserRowMapper() {
        throw new UnsupportedOperationException(
                "Utility class cannot be instantiated."
        );
    }

    /**
     * Maps the current row of a ResultSet to a User.
     *
     * @param resultSet current ResultSet
     * @return populated User object
     * @throws SQLException if a database access error occurs
     */
    public static User map(ResultSet resultSet) throws SQLException {

        Role role = new Role();

        role.setRoleId(resultSet.getInt("role_id"));
        role.setRoleName(resultSet.getString("role_name"));
        role.setDescription(resultSet.getString("description"));

        Timestamp createdTimestamp = resultSet.getTimestamp("created_at");
        Timestamp updatedTimestamp = resultSet.getTimestamp("updated_at");

        LocalDateTime createdAt =
                createdTimestamp != null
                        ? createdTimestamp.toLocalDateTime()
                        : null;

        LocalDateTime updatedAt =
                updatedTimestamp != null
                        ? updatedTimestamp.toLocalDateTime()
                        : null;

        return User.builder()
                .userId(resultSet.getInt("user_id"))
                .role(role)
                .fullName(resultSet.getString("full_name"))
                .username(resultSet.getString("username"))
                .password(resultSet.getString("password"))
                .email(resultSet.getString("email"))
                .phone(resultSet.getString("phone"))
                .active(resultSet.getBoolean("is_active"))
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

}