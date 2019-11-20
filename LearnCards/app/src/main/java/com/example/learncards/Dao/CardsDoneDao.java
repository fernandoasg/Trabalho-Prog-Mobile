package com.example.learncards.Dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.learncards.Entities.CardsDone;

import java.util.List;

@Dao
public interface CardsDoneDao extends BaseDao<CardsDone>{

    @Query("SELECT * FROM cards_done WHERE user_fk = :userId")
    List<CardsDone> getAllCardsDoneOfUser(long userId);

    @Query("SELECT * FROM cards_done WHERE user_fk = :userId AND card_fk = :cardId")
    CardsDone getCardDoneOfUser(long userId, long cardId);
}