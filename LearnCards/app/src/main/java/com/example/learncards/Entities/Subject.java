package com.example.learncards.Entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;

import java.util.List;

@Entity(tableName = "subject")
public class Subject {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String subArea;

    @Relation(parentColumn = "id", entityColumn = "subject_fk", entity = Card.class)
    private List<Card> cards;

    public Subject(long i, String n, String s){
        this.id = i;
        this.name = n;
        this.subArea = s;
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

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}