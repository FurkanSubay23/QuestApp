package com.questapp.exception;

public class ErrorHandling extends RuntimeException {
    public ErrorHandling() {
        super();
    }

    public ErrorHandling(String message) {
        super(message);
    }

    public ErrorHandling(String message, Throwable cause) {
        super(message, cause);
    }

    public ErrorHandling(Throwable cause) {
        super(cause);
    }
}
