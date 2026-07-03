package com.librarymanagement.exception;

/**
 * Exception thrown when a requested resource cannot be found.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public class ResourceNotFoundException extends ApplicationException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}