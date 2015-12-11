package com.cloudskol.cloudroid.common;

/**
 * @author tham
 */
public class CloudroidException extends Exception {
    public CloudroidException() {
        super();
    }

    public CloudroidException(String message) {
        super(message);
    }

    public CloudroidException(Throwable throwable) {
        super(throwable);
    }

    public CloudroidException(String code, Throwable throwable) {
        super(code, throwable);
    }
}
