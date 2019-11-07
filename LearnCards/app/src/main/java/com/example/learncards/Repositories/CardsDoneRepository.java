package com.example.learncards.Repositories;

import android.app.Application;

import com.example.learncards.Dao.CardsDoneDao;
import com.example.learncards.Database.AppDatabase;
import com.example.learncards.Entities.CardsDone;

import java.util.List;

public class CardsDoneRepository {

    private List<CardsDone> allCardsDone;

    public CardsDoneRepository(Application application, long userId){
        AppDatabase db = AppDatabase.getInstance(application);
        CardsDoneDao cardsDoneDao = db.cardsDoneDao();
        allCardsDone = cardsDoneDao.getAllCardsDoneOfUser(userId);
    }

    public List<CardsDone> getCardsDones(){
        return allCardsDone;
    }
}
