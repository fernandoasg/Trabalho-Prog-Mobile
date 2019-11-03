package com.example.learncards.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "cards_done",
        foreignKeys = {
                @ForeignKey(entity = User.class,
                        parentColumns = "id",
                        childColumns = "user_fk"),
                @ForeignKey(entity = Card.class,
                        parentColumns = "id",
                        childColumns = "card_fk")
        })

public class CardsDone {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private float rating;
    private String ratingText;

    @ColumnInfo(name = "user_fk")
    private long userFk;
    @ColumnInfo(name = "card_fk")
    private long cardFk;

    public CardsDone(long id, float rating, String rating_text, long user_fk, long card_fk) {
        this.id = id;
        this.rating = rating;
        this.ratingText = rating_text;
        this.userFk = user_fk;
        this.cardFk = card_fk;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getRatingText() {
        return ratingText;
    }

    public void setRatingText(String ratingText) {
        this.ratingText = ratingText;
    }

    public long getUserFk() {
        return userFk;
    }

    public void setUserFk(long user_fk) {
        this.userFk = user_fk;
    }

    public long getCardFk() {
        return cardFk;
    }

    public void setCardFk(long card_fk) {
        this.cardFk = card_fk;
    }
}
