package cz.bonoman.service;

import java.time.LocalDateTime;

public class ErrorResponse {
    public String message;
    public StackTraceElement[] stackTrace;
    public LocalDateTime timestamp;

    public ErrorResponse(String message, StackTraceElement[] stackTrace, LocalDateTime timestamp){
        this.message = message;
        this.stackTrace = stackTrace;
        this.timestamp = timestamp;
    }

    public String getMessage(){ return message; }
    public StackTraceElement[] getStackTrace(){ return stackTrace; }
    public LocalDateTime getTimestamp(){ return timestamp; }
}
