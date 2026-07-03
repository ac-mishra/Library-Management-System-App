package com.librarymanagement.repository;

import com.librarymanagement.entity.User;

import java.util.Optional;

/**
 * Repository interface for User.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public interface UserRepository
        extends CrudRepository<User, Integer> {

    /**
     * Finds a user by username.
     *
     * @param username username
     * @return Optional User
     */
    Optional<User> findByUsername(String username);

    /**
     * Finds a user by email.
     *
     * @param email email
     * @return Optional User
     */
    Optional<User> findByEmail(String email);

    /**
     * Checks whether a username already exists.
     *
     * @param username username
     * @return true if exists
     */
    boolean existsByUsername(String username);

    /**
     * Checks whether an email already exists.
     *
     * @param email email
     * @return true if exists
     */
    boolean existsByEmail(String email);

}