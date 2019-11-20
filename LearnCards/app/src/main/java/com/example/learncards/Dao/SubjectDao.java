package com.example.learncards.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.learncards.Entities.Subject;
import com.example.learncards.Entities.SubjectWithCards;

import java.util.List;

@Dao
public interface SubjectDao extends BaseDao<Subject>{

    @Query("SELECT * FROM subject ORDER BY name DESC")
    List<Subject> getAllSubjects();

    @Query("SELECT * FROM subject JOIN user_subject ON subject_fk = subject.id WHERE user_fk = :userID")
    List<Subject> getAllSubjectsSelectedByUser(long userID);

    @Query("SELECT * FROM subject WHERE subject.id not in (select subject_fk from user_subject where user_fk = :userId)")
    List<Subject> getAllSubjectsNotSelectedByUser(long userId);

    @Transaction
    @Query("SELECT * FROM subject ORDER BY name DESC")
    List<SubjectWithCards> getAllSubjectsWithCards();

    @Transaction
    @Query("SELECT * FROM subject WHERE id = :subjectId")
    List<SubjectWithCards> loadAllOfSubject(long subjectId);
}
