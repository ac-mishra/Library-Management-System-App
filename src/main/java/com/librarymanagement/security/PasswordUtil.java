package com.librarymanagement.security;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Utility class for password hashing and verification using BCrypt.
 *
 * <p>This class provides methods to securely hash passwords before
 * storing them in the database and verify passwords during login.</p>
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public final class PasswordUtil {

    /**
     * BCrypt work factor.
     * Higher values increase security but require more processing time.
     */
    private static final int LOG_ROUNDS = 12;

    /**
     * Private constructor to prevent instantiation.
     */
    private PasswordUtil() {
        throw new UnsupportedOperationException(
                "Utility class cannot be instantiated."
        );
    }

    /**
     * Hashes a plain-text password.
     *
     * @param plainPassword the password entered by the user
     * @return BCrypt hashed password
     * @throws IllegalArgumentException if the password is null or blank
     */
    public static String hashPassword(String plainPassword) {

        validatePassword(plainPassword);

        return BCrypt.hashpw(
                plainPassword,
                BCrypt.gensalt(LOG_ROUNDS)
        );
    }

    /**
     * Verifies a plain-text password against a BCrypt hash.
     *
     * @param plainPassword user entered password
     * @param hashedPassword password stored in database
     * @return true if passwords match; otherwise false
     */
    public static boolean verifyPassword(
            String plainPassword,
            String hashedPassword) {

        validatePassword(plainPassword);

        if (hashedPassword == null || hashedPassword.isBlank()) {
            return false;
        }

        return BCrypt.checkpw(
                plainPassword,
                hashedPassword
        );
    }

    /**
     * Validates password input.
     *
     * @param password password to validate
     */
    private static void validatePassword(String password) {

        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException(
                    "Password cannot be null or blank."
            );
        }

    }

}