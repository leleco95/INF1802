package com.example.hellotwitter;

import java.util.UUID;
import com.datastax.driver.core.LocalDate;

public class Tweet {
    private UUID id;
    private String user;
    private String message;
    private LocalDate date;

    public Tweet() {}

    public Tweet(final UUID id, final String user, final String message, final LocalDate date) {
        this.id = id;
        this.user = user;
        this.message = message;
        this.date = date;
    }

    public UUID getId() { return id; }
    public String getUser() { return user; }
    public String getMessage() { return message; }
    public LocalDate getDate() { return date; }

    public void setId(UUID id) { this.id = id; }
    public void setUser(String user) { this.user = user; }
    public void setMessage(String message) { this.message = message; }
    public void setDate(LocalDate date) { this.date = date; }

    public String toString() { return "[" + date + "]" + user + ": " + message; }
}