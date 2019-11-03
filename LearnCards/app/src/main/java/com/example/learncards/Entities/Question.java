package com.example.learncards.Entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "question")
public class Question {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private long card_fk;
    private String description;
    private String alternativeA;
    private String alternativeB;
    private String alternativeC;
    private String alternativeD;
    private String alternativeE;
    private char answer;

    public Question(long id, long card_fk, String description, String alternativeA, String alternativeB, String alternativeC, String alternativeD, String alternativeE, char answer) {
        this.id = id;
        this.card_fk = card_fk;
        this.description = description;
        this.alternativeA = alternativeA;
        this.alternativeB = alternativeB;
        this.alternativeC = alternativeC;
        this.alternativeD = alternativeD;
        this.alternativeE = alternativeE;
        this.answer = answer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAlternativeA() {
        return alternativeA;
    }

    public long getCard_fk() {
        return card_fk;
    }

    public void setCard_fk(long card_fk) {
        this.card_fk = card_fk;
    }

    public void setAlternativeA(String alternativeA) {
        this.alternativeA = alternativeA;
    }

    public String getAlternativeB() {
        return alternativeB;
    }

    public void setAlternativeB(String alternativeB) {
        this.alternativeB = alternativeB;
    }

    public String getAlternativeC() {
        return alternativeC;
    }

    public void setAlternativeC(String alternativeC) {
        this.alternativeC = alternativeC;
    }

    public String getAlternativeD() {
        return alternativeD;
    }

    public void setAlternativeD(String alternativeD) {
        this.alternativeD = alternativeD;
    }

    public String getAlternativeE() {
        return alternativeE;
    }

    public void setAlternativeE(String alternativeE) {
        this.alternativeE = alternativeE;
    }

    public char getAnswer() {
        return answer;
    }

    public void setAnswer(char answer) {
        this.answer = answer;
    }
}
