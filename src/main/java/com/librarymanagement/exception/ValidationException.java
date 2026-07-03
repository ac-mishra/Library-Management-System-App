package com.librarymanagement.exception;

/**
 * Exception thrown when validation fails.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public class ValidationException extends ApplicationException {

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}