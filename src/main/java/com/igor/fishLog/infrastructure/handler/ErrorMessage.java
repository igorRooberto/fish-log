package com.igor.fishLog.infrastructure.handler;

import java.time.Instant;

public class ErrorMessage {

    private Instant timeStamp;

    private int status;

    private String error;

    private String message;

    private String path;

    public ErrorMessage(Instant timeStamp, int status, String error, String message, String path) {
        this.timeStamp = timeStamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
