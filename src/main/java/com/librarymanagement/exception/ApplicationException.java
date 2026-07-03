package com.librarymanagement.exception;

/**
 * Base exception for the Library Management System.
 *
 * <p>All custom application exceptions should extend this class.</p>
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public class ApplicationException extends Exception {

    /**
     * Creates an exception with a message.
     *
     * @param message exception message
     */
    public ApplicationException(String message) {
        super(message);
    }

    /**
     * Creates an exception with a message and cause.
     *
     * @param message exception message
     * @param cause root cause
     */
    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

}