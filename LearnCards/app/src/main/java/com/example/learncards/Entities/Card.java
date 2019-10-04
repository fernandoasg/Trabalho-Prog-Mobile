package com.example.learncards.Entities;

import java.util.List;

public class Card {

    private long id;
    private String name;
    private String description;
    private String context;
    private float rating;
    private List<Question> questions;

    public Card(long i, String n, String d, String c, float r, List<Question> l){
        this.id = i;
        this.name = n;
        this.description = d;
        this.context = c;
        this.rating = r;
        this.questions = l;
    }
}