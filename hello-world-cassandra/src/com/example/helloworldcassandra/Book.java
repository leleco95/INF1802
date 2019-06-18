package com.example.helloworldcassandra;

import java.util.UUID;

public class Book {

    private UUID id;
    private String title;
    private String author;
    private String subject;
    private String publisher;

    Book() {
    }

    public Book(UUID id, String title, String author, String subject) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.subject = subject;
    }

    public UUID getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getSubject() { return subject; }
    public String getPublisher() { return publisher; }

    public void setId(UUID id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setSubject(String subject) { this.subject = subject; }
    public void setPublisher(String publisher) { this.publisher = publisher; }
}
