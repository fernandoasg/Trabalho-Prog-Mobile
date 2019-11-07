package com.example.learncards.Entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "card",
        indices = {@Index("id")})
public class Card {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private long subject_fk;
    private String name;
    private String description;
    private String context;
    private float rating;

    public Card(long id, long subject_fk, String name, String description, String context, float rating) {
        this.id = id;
        this.subject_fk = subject_fk;
        this.name = name;
        this.description = description;
        this.context = context;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSubject_fk() {
        return subject_fk;
    }

    public void setSubject_fk(long subject_fk) {
        this.subject_fk = subject_fk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

}