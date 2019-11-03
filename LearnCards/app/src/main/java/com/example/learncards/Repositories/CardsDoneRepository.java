package com.example.learncards.Repositories;

import android.app.Application;

import com.example.learncards.Dao.CardsDoneDao;
import com.example.learncards.Database.AppDatabase;
import com.example.learncards.Entities.CardsDone;

import java.util.List;

public class CardsDoneRepository {

    private CardsDoneDao cardsDoneDao;
    private List<CardsDone> cardsDones;

    public CardsDoneRepository(Application application, long userId){
        AppDatabase db = AppDatabase.getInstance(application);
        cardsDoneDao = db.cardsDoneDao();
        cardsDones = cardsDoneDao.getAllCardsDoneOfUser(userId);
    }
}
