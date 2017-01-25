package com.pyshankov.hairdresser.exception;

/**
 * Created by pyshankov on 1/25/17.
 */
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
