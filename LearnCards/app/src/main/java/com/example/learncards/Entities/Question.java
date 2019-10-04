package com.example.learncards.Entities;

public class Question {

    private long id;
    private String description;
    private String alternativeA;
    private String alternativeB;
    private String alternativeC;
    private String alternativeD;
    private String alternativeE;
    private char answer;

    public Question(long i, String a, String b, String c, String d, String e, char answer){
        this.id = i;
        this.alternativeA = a;
        this.alternativeB = b;
        this.alternativeC = c;
        this.alternativeD = d;
        this.alternativeE = e;
        this.answer = answer;
    }
}
