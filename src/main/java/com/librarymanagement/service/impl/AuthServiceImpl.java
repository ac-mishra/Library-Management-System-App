package com.librarymanagement.service.impl;

import com.librarymanagement.entity.User;
import com.librarymanagement.repository.UserRepository;
import com.librarymanagement.security.PasswordUtil;
import com.librarymanagement.service.AuthService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

/**
 * Authentication service implementation.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public class AuthServiceImpl implements AuthService {

    private static final Logger LOGGER =
            LogManager.getLogger(AuthServiceImpl.class);

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> login(String username, String password) {

        Optional<User> optionalUser =
                userRepository.findByUsername(username);

        if (optionalUser.isEmpty()) {

            LOGGER.warn("Invalid username: {}", username);

            return Optional.empty();

        }

        User user = optionalUser.get();

        if (!user.getActive()) {

            LOGGER.warn("Inactive account: {}", username);

            return Optional.empty();

        }

        if (!PasswordUtil.verifyPassword(
                password,
                user.getPassword()
        )) {

            LOGGER.warn("Invalid password for {}", username);

            return Optional.empty();

        }

        LOGGER.info("Login successful: {}", username);

        return optionalUser;

    }

}