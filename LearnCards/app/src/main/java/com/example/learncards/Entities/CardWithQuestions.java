package com.example.learncards.Entities;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

public class CardWithQuestions {
    @Embedded
    public Card card;

    @Relation(parentColumn = "id", entityColumn = "card_fk", entity = Question.class)
    public List<Question> questions;
}
