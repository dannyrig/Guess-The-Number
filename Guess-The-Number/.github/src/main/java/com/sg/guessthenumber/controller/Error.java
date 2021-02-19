package com.sg.guessthenumber.controller;

import java.time.LocalDateTime;

// This class is used for catching errors and displaying them in Postman.
public class Error {

    // Create variables for retrieving the current time
    // and printing a message for when an exception is caught.
    private LocalDateTime timestamp = LocalDateTime.now();
    private String message;

    // Method for getting the time when the error was caught.
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    // Method for setting the time when the error was caught
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    // Method for getting the custom message displayed on the Postman console.
    public String getMessage() {
        return message;
    }

    // Method for setting the message that is to displayed for a certain error.
    public void setMessage(String message) {
        this.message = message;
    }
}
