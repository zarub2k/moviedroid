package com.cloudskol.moviedroid.common;

/**
 * @author tham
 */
public class MoviedroidException extends Exception {
    public MoviedroidException() {
        super();
    }

    public MoviedroidException(String message) {
        super(message);
    }

    public MoviedroidException(Throwable throwable) {
        super(throwable);
    }

    public MoviedroidException(String code, Throwable throwable) {
        super(code, throwable);
    }
}
