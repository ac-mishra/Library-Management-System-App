package com.librarymanagement.exception;

/**
 * Exception thrown when attempting to create a duplicate resource.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public class DuplicateResourceException extends ApplicationException {

    public DuplicateResourceException(String message) {
        super(message);
    }

    public DuplicateResourceException(String message, Throwable cause) {
        super(message, cause);
    }
}