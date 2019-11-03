package com.example.learncards.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.learncards.Entities.Subject;

import java.util.List;

@Dao
public interface SubjectDao {

    @Query("SELECT * FROM subject ORDER BY name DESC")
    List<Subject> getAllSubjects();

    @Query("SELECT * FROM subject WHERE id = :subjectId")
    List<Subject> loadAllOfSubject(long subjectId);
}
