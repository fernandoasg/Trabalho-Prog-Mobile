package com.example.learncards.Dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.learncards.Entities.CardWithQuestions;

import java.util.List;

@Dao
public interface CardDao {

    @Transaction
    @Query("SELECT * FROM card ORDER BY name DESC")
    List<CardWithQuestions> getAllCards();

    @Transaction
    @Query("SELECT * FROM card WHERE subject_fk = :subjectId")
    List<CardWithQuestions> loadAllCardsOfSubject(long subjectId);
}
