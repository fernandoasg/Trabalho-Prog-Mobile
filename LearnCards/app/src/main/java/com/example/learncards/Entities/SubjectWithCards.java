package com.example.learncards.Entities;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

public class SubjectWithCards {

    @Embedded
    public Subject subject;

    @Relation(parentColumn = "id", entityColumn = "subject_fk", entity = Card.class)
    public List<Card> cards;
}
