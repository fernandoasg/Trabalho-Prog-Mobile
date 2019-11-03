package com.example.learncards.Entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;

import java.util.List;

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