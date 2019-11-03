package com.example.learncards.Repositories;

import android.app.Application;

import com.example.learncards.Dao.CardDao;
import com.example.learncards.Database.AppDatabase;
import com.example.learncards.Entities.Card;
import com.example.learncards.Entities.CardWithQuestions;

import java.util.List;

public class CardRepository {

    private CardDao cardDao;
    private List<CardWithQuestions> allCards;

    public CardRepository(Application application){
        AppDatabase db = AppDatabase.getInstance(application);
        cardDao = db.cardDao();
        allCards = cardDao.getAllCards();
    }

    public List<CardWithQuestions> getAllCards(){
        return allCards;
    }
}
