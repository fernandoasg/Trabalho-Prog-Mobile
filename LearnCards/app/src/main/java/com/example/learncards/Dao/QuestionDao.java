package com.example.learncards.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.learncards.Entities.Question;

import java.util.List;

@Dao
public interface QuestionDao {

    @Insert
    void insert(Question question);

    @Update
    void update(Question question);

    @Delete
    void delete(Question question);

    @Query("DELETE FROM question")
    void deleteAllQuestions();

    @Query("SELECT * FROM question")
    List<Question> getAllQuestions();

    @Query("SELECT * FROM question WHERE card_fk = :cardId")
    List<Question> getAllQuestionOfCard(long cardId);
}
