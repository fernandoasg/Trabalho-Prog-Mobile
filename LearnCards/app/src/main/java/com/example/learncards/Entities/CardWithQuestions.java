package com.example.learncards.Entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class CardWithQuestions {
    @Embedded
    public Card card;

    @Relation(parentColumn = "id", entityColumn = "card_fk", entity = Question.class)
    public List<Question> questions;
}
