package com.hifish.app.exception;

/**
 * for HTTP 400 errors
 */
public final class DataFormatException extends RuntimeException {
    /**
     * Instantiates a new Data format exception.
     */
    public DataFormatException() {
        super();
    }

    /**
     * Instantiates a new Data format exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public DataFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Data format exception.
     *
     * @param message the message
     */
    public DataFormatException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Data format exception.
     *
     * @param cause the cause
     */
    public DataFormatException(Throwable cause) {
        super(cause);
    }
}