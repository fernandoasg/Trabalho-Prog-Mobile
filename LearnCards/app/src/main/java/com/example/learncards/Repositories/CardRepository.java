package com.example.learncards.Repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.example.learncards.Dao.CardDao;
import com.example.learncards.Database.AppDatabase;
import com.example.learncards.Entities.Card;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class CardRepository {

    private CardDao cardDao;
    private List<Card> allCards;

    public CardRepository(Application application){
        AppDatabase db = AppDatabase.getInstance(application);
        cardDao = db.cardDao();
        try {
            allCards = new GetAllCardsTask().execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Card> getAllCards(){
        return allCards;
    }

    private class GetAllCardsTask extends AsyncTask<Void, Void, List<Card>> {
        @Override
        protected List<Card> doInBackground(Void... url){
            return cardDao.getAllCards();
        }
    }

}
