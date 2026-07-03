package com.librarymanagement.validation;

import java.util.regex.Pattern;

/**
 * Utility class for validating user-related input.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public final class UserValidator {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^[6-9]\\d{9}$");

    private static final Pattern USERNAME_PATTERN =
            Pattern.compile("^[A-Za-z][A-Za-z0-9_]{3,19}$");

    private UserValidator() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Validates full name.
     *
     * @param fullName user's full name
     * @return validation result
     */
    public static ValidationResult validateFullName(String fullName) {

        if (fullName == null || fullName.isBlank()) {
            return ValidationResult.failure("Full name is required.");
        }

        if (fullName.length() < 3) {
            return ValidationResult.failure(
                    "Full name must contain at least 3 characters."
            );
        }

        return ValidationResult.success();
    }

    /**
     * Validates username.
     *
     * @param username username
     * @return validation result
     */
    public static ValidationResult validateUsername(String username) {

        if (username == null || username.isBlank()) {
            return ValidationResult.failure("Username is required.");
        }

        if (!USERNAME_PATTERN.matcher(username).matches()) {
            return ValidationResult.failure(
                    "Username must start with a letter and contain 4 to 20 characters."
            );
        }

        return ValidationResult.success();
    }

    /**
     * Validates email address.
     *
     * @param email email address
     * @return validation result
     */
    public static ValidationResult validateEmail(String email) {

        if (email == null || email.isBlank()) {
            return ValidationResult.failure("Email is required.");
        }

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            return ValidationResult.failure("Invalid email address.");
        }

        return ValidationResult.success();
    }

    /**
     * Validates Indian mobile number.
     *
     * @param phone phone number
     * @return validation result
     */
    public static ValidationResult validatePhone(String phone) {

        if (phone == null || phone.isBlank()) {
            return ValidationResult.failure("Phone number is required.");
        }

        if (!PHONE_PATTERN.matcher(phone).matches()) {
            return ValidationResult.failure(
                    "Phone number must contain exactly 10 digits."
            );
        }

        return ValidationResult.success();
    }

    /**
     * Validates password strength.
     *
     * Rules:
     * - Minimum 8 characters
     * - At least one uppercase letter
     * - At least one lowercase letter
     * - At least one digit
     * - At least one special character
     *
     * @param password password
     * @return validation result
     */
    public static ValidationResult validatePassword(String password) {

        if (password == null || password.isBlank()) {
            return ValidationResult.failure("Password is required.");
        }

        if (password.length() < 8) {
            return ValidationResult.failure(
                    "Password must contain at least 8 characters."
            );
        }

        if (!password.matches(".*[A-Z].*")) {
            return ValidationResult.failure(
                    "Password must contain at least one uppercase letter."
            );
        }

        if (!password.matches(".*[a-z].*")) {
            return ValidationResult.failure(
                    "Password must contain at least one lowercase letter."
            );
        }

        if (!password.matches(".*\\d.*")) {
            return ValidationResult.failure(
                    "Password must contain at least one digit."
            );
        }

        if (!password.matches(".*[@#$%^&+=!].*")) {
            return ValidationResult.failure(
                    "Password must contain at least one special character."
            );
        }

        return ValidationResult.success();
    }

}