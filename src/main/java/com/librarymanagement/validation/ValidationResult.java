package com.librarymanagement.validation;

/**
 * Represents the result of a validation operation.
 *
 * Contains whether validation succeeded and an optional message.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public final class ValidationResult {

    private final boolean valid;

    private final String message;

    private ValidationResult(boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }

    /**
     * Creates a successful validation result.
     *
     * @return successful ValidationResult
     */
    public static ValidationResult success() {
        return new ValidationResult(true, "Validation successful.");
    }

    /**
     * Creates a failed validation result.
     *
     * @param message validation failure message
     * @return failed ValidationResult
     */
    public static ValidationResult failure(String message) {
        return new ValidationResult(false, message);
    }

    /**
     * Returns whether validation succeeded.
     *
     * @return true if valid
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * Returns the validation message.
     *
     * @return validation message
     */
    public String getMessage() {
        return message;
    }

}