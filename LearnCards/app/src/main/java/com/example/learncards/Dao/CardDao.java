package com.example.learncards.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.learncards.Entities.Card;
import com.example.learncards.Entities.CardWithQuestions;

import java.util.List;

@Dao
public interface CardDao {

    @Transaction
    @Query("SELECT * FROM card ORDER BY name DESC")
    List<Card> getAllCards();

    @Transaction
    @Query("SELECT * FROM card WHERE user_fk = :user_fk")
    List<Card> getUserCards(long user_fk);

    @Transaction
    @Query("SELECT * FROM card WHERE subject_fk = :subjectId")
    List<CardWithQuestions> loadAllCardsOfSubject(long subjectId);

    @Transaction
    @Query("SELECT * FROM card WHERE id = :cardId")
    CardWithQuestions loadCard(long cardId);

    @Insert
    void insert(Card card);
}
