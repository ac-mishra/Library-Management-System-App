package com.librarymanagement.dao.impl;

import com.librarymanagement.common.constants.UserQueries;
import com.librarymanagement.config.DBConnection;
import com.librarymanagement.entity.User;
import java.util.Objects;
import com.librarymanagement.mapper.UserRowMapper;
import com.librarymanagement.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserRepository {

    private static final Logger LOGGER =
            LogManager.getLogger(UserDAOImpl.class);

    public UserDAOImpl() {
    }

    @Override
    public User save(User user) {

        Objects.requireNonNull(user, "User cannot be null.");
        Objects.requireNonNull(user.getRole(), "Role cannot be null.");

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                UserQueries.INSERT_USER,
                                Statement.RETURN_GENERATED_KEYS
                        )
        ) {

            preparedStatement.setInt(
                    1,
                    user.getRole().getRoleId()
            );

            preparedStatement.setString(
                    2,
                    user.getFullName()
            );

            preparedStatement.setString(
                    3,
                    user.getUsername()
            );

            preparedStatement.setString(
                    4,
                    user.getPassword()
            );

            preparedStatement.setString(
                    5,
                    user.getEmail()
            );

            preparedStatement.setString(
                    6,
                    user.getPhone()
            );

            preparedStatement.setBoolean(
                    7,
                    user.getActive()
            );

            int rows =
                    preparedStatement.executeUpdate();

            if (rows == 0) {

                throw new RuntimeException(
                        "Unable to save user."
                );
            }

            try (
                    ResultSet generatedKeys =
                            preparedStatement.getGeneratedKeys()
            ) {

                if (generatedKeys.next()) {

                    user.setUserId(
                            generatedKeys.getInt(1)
                    );

                }

            }

            LOGGER.info(
                    "User saved successfully : {}",
                    user.getUsername()
            );

            return user;

        } catch (SQLException exception) {

            LOGGER.error(
                    "Database error while saving user.",
                    exception
            );

            throw new RuntimeException(
                    "Failed to save user.",
                    exception
            );

        }

    }

    @Override
    public boolean update(User user) {

        try (
                Connection connection = DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(UserQueries.UPDATE_USER)
        ) {

            preparedStatement.setInt(1, user.getRole().getRoleId());
            preparedStatement.setString(2, user.getFullName());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPhone());
            preparedStatement.setBoolean(7, user.getActive());
            preparedStatement.setInt(8, user.getUserId());

            int rowsAffected = preparedStatement.executeUpdate();

            LOGGER.info("User updated successfully: {}", user.getUsername());

            return rowsAffected > 0;

        } catch (SQLException exception) {

            LOGGER.error("Failed to update user.", exception);

            throw new RuntimeException(
                    "Unable to update user.",
                    exception
            );

        }

    }

    @Override
    public boolean delete(Integer userId) {

        try (
                Connection connection = DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(UserQueries.DELETE_USER)
        ) {

            preparedStatement.setInt(1, userId);

            int rowsAffected = preparedStatement.executeUpdate();

            LOGGER.info("User deleted successfully. ID={}", userId);

            return rowsAffected > 0;

        } catch (SQLException exception) {

            LOGGER.error("Failed to delete user.", exception);

            throw new RuntimeException(
                    "Unable to delete user.",
                    exception
            );

        }

    }

    @Override
    public Optional<User> findById(Integer userId) {

        try (
                Connection connection = DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(UserQueries.FIND_BY_ID)
        ) {

            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {

                    User user = UserRowMapper.map(resultSet);

                    return Optional.of(user);

                }

            }

        } catch (SQLException exception) {

            LOGGER.error("Failed to fetch user by ID.", exception);

            throw new RuntimeException(
                    "Unable to fetch user.",
                    exception
            );

        }

        return Optional.empty();

    }

    @Override
    public Optional<User> findByUsername(String username) {

        try (
                Connection connection = DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(UserQueries.FIND_BY_USERNAME)
        ) {

            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {

                    return Optional.of(UserRowMapper.map(resultSet));

                }

            }

        } catch (SQLException exception) {

            LOGGER.error("Error finding user by username.", exception);

            throw new RuntimeException(
                    "Unable to find user by username.",
                    exception
            );

        }

        return Optional.empty();

    }

    @Override
    public Optional<User> findByEmail(String email) {

        try (
                Connection connection = DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(UserQueries.FIND_BY_EMAIL)
        ) {

            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {

                    return Optional.of(UserRowMapper.map(resultSet));

                }

            }

        } catch (SQLException exception) {

            LOGGER.error("Error finding user by email.", exception);

            throw new RuntimeException(
                    "Unable to find user by email.",
                    exception
            );

        }

        return Optional.empty();

    }

    @Override
    public List<User> findAll() {

        List<User> users = new ArrayList<>();

        try (
                Connection connection = DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(UserQueries.FIND_ALL);

                ResultSet resultSet =
                        preparedStatement.executeQuery()
        ) {

            while (resultSet.next()) {

                users.add(
                        UserRowMapper.map(resultSet)
                );

            }

        } catch (SQLException exception) {

            LOGGER.error("Error fetching users.", exception);

            throw new RuntimeException(
                    "Unable to fetch users.",
                    exception
            );

        }

        return users;

    }

    @Override
    public boolean existsByUsername(String username) {

        try (
                Connection connection = DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(UserQueries.EXISTS_BY_USERNAME)
        ) {

            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {

                    return resultSet.getInt(1) > 0;

                }

            }

        } catch (SQLException exception) {

            LOGGER.error("Error checking username.", exception);

            throw new RuntimeException(
                    "Unable to verify username.",
                    exception
            );

        }

        return false;

    }

    @Override
    public boolean existsByEmail(String email) {

        try (
                Connection connection = DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(UserQueries.EXISTS_BY_EMAIL)
        ) {

            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {

                    return resultSet.getInt(1) > 0;

                }

            }

        } catch (SQLException exception) {

            LOGGER.error("Error checking email.", exception);

            throw new RuntimeException(
                    "Unable to verify email.",
                    exception
            );

        }

        return false;

    }


}