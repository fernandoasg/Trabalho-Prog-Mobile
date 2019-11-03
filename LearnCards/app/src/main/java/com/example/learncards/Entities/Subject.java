package com.example.learncards.Entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

@Entity(tableName = "subject")
public class Subject {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String subArea;

    public Subject(long id, String name, String subArea) {
        this.id = id;
        this.name = name;
        this.subArea = subArea;
    }

    @Ignore
    public Subject(String name, String subArea) {
        this.name = name;
        this.subArea = subArea;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubArea() {
        return subArea;
    }

    public void setSubArea(String subArea) {
        this.subArea = subArea;
    }
}