package com.example.learncards.Entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.io.Serializable;


@Entity(tableName = "subject")
public class Subject implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String subArea;

    // Utilizado na view de subjects pra selecao multipla
    private boolean isChecked = false;

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

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
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