package com.librarymanagement.exception;

/**
 * Exception thrown when authentication fails.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public class AuthenticationException extends ApplicationException {

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}