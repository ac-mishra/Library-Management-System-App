package com.librarymanagement.service;

import com.librarymanagement.entity.User;

import java.util.Optional;

/**
 * Service interface for user authentication.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public interface AuthService {

    /**
     * Authenticates a user using username and password.
     *
     * @param username username
     * @param password plain text password
     * @return authenticated user if credentials are valid
     */
    Optional<User> login(String username, String password);

}