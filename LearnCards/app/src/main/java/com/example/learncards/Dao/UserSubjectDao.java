package com.example.learncards.Dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.learncards.Entities.UserSubject;

import java.util.List;

@Dao
public interface UserSubjectDao {

    @Insert
    void insert(UserSubject userSubject);

    @Update
    void update(UserSubject userSubject);

    @Delete
    void delete(UserSubject userSubject);

    @Query("DELETE FROM user_subject")
    void deleteAllUserSubject();

    @Query("SELECT * FROM user_subject")
    List<UserSubject> getAllUserSubject();
}
