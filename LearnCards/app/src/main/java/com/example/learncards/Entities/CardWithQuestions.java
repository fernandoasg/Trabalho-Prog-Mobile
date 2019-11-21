package com.example.learncards.Entities;

import androidx.room.Embedded;
import androidx.room.Ignore;
import androidx.room.Relation;

import java.util.List;

public class CardWithQuestions {
    @Embedded
    public Card card;

    @Ignore
    public boolean done;

    @Relation(parentColumn = "id", entityColumn = "card_fk", entity = Question.class)
    public List<Question> questions;
}
