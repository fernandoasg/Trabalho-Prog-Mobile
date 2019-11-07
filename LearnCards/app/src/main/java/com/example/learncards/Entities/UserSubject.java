package com.example.learncards.Entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_subject",
        foreignKeys = {
                @ForeignKey(
                        entity = User.class,
                        parentColumns = "id",
                        childColumns = "user_fk"),

                @ForeignKey(
                        entity = Subject.class,
                        parentColumns = "id",
                        childColumns = "subject_fk")
        },
        indices = {
                @Index("user_fk"),
                @Index("subject_fk")})
public class UserSubject {

    public UserSubject(long user_fk, long subject_fk) {
        this.user_fk = user_fk;
        this.subject_fk = subject_fk;
    }

    @PrimaryKey(autoGenerate = true)
    private long id;

    private long user_fk;
    private long subject_fk;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_fk() {
        return user_fk;
    }

    public void setUser_fk(long user_fk) {
        this.user_fk = user_fk;
    }

    public long getSubject_fk() {
        return subject_fk;
    }

    public void setSubject_fk(long subject_fk) {
        this.subject_fk = subject_fk;
    }
}
