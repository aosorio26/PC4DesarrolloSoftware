package com.petalert.model;

import java.time.Instant;

public class Resena {
    private String ownerName;
    private int rating;
    private String comment;
    private Instant createdAt;

    public Resena() {
    }

    public Resena(String ownerName, int rating, String comment, Instant createdAt) {
        this.ownerName = ownerName;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
