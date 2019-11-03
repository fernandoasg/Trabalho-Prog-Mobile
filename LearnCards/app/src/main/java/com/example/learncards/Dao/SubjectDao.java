package com.example.learncards.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.learncards.Entities.Subject;
import com.example.learncards.Entities.SubjectWithCards;

import java.util.List;

@Dao
public interface SubjectDao {


    @Insert
    void insert(Subject subject);


    @Query("SELECT * FROM subject ORDER BY name DESC")
    List<Subject> getAllSubjects();

    @Transaction
    @Query("SELECT * FROM subject ORDER BY name DESC")
    List<SubjectWithCards> getAllSubjectsWithCards();

    @Transaction
    @Query("SELECT * FROM subject WHERE id = :subjectId")
    List<SubjectWithCards> loadAllOfSubject(long subjectId);
}