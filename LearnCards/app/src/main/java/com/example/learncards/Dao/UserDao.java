package com.example.learncards.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.learncards.Entities.Subject;
import com.example.learncards.Entities.User;

import java.util.List;

@Dao
public interface UserDao extends BaseDao<User>{

    @Query("DELETE FROM user")
    void deleteAllUsers();

    @Query("SELECT * FROM user")
    List<User> getAllUsers();

    @Query("SELECT * FROM subject JOIN user_subject ON subject.id == subject_fk WHERE user_fk = :userId")
    List<Subject> getAllSubjectsFromUser(long userId);
}
