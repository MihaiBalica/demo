package org.example.common;

public class RequestFailedException extends Throwable {
    public RequestFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
