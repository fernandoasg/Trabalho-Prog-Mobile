package com.example.learncards.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.learncards.Entities.Card;

import java.util.List;

@Dao
public interface CardDao {

    @Query("SELECT * FROM card ORDER BY name DESC")
    List<Card> getAllCards();

    @Query("SELECT * FROM card WHERE subject_fk = :subjectId")
    List<Card> loadAllCardsOfSubject(long subjectId);
}
