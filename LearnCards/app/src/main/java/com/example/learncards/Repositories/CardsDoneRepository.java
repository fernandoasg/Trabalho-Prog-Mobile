package com.example.learncards.Repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.example.learncards.Dao.CardsDoneDao;
import com.example.learncards.Database.AppDatabase;
import com.example.learncards.Entities.CardsDone;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class CardsDoneRepository {

    private List<CardsDone> allCardsDone;
    private CardsDoneDao cardsDoneDao;

    public CardsDoneRepository(Application application, long userId){
        AppDatabase db = AppDatabase.getInstance(application);
        cardsDoneDao = db.cardsDoneDao();
        try {
            allCardsDone = new GetAllCardsDoneOfUserAsyncTask(cardsDoneDao).execute(userId).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void insert(CardsDone cardsDone){
        new InsertCardDoneAsyncTask(cardsDoneDao).execute(cardsDone);
    }

    public List<CardsDone> getCardsDones(){
        return allCardsDone;
    }

    private static class InsertCardDoneAsyncTask extends AsyncTask<CardsDone, Void, Void> {
        private CardsDoneDao cardsDoneDao;
        private InsertCardDoneAsyncTask(CardsDoneDao cardsDoneDao){
            this.cardsDoneDao = cardsDoneDao;
        }

        @Override
        protected Void doInBackground(CardsDone... cardsDones) {
            cardsDoneDao.insert(cardsDones[0]);
            return null;
        }
    }

    private static class GetAllCardsDoneOfUserAsyncTask extends AsyncTask<Long, Void, List<CardsDone>>{
        private CardsDoneDao cardsDoneDao;
        private GetAllCardsDoneOfUserAsyncTask(CardsDoneDao cardsDoneDao){
            this.cardsDoneDao = cardsDoneDao;
        }

        @Override
        protected List<CardsDone> doInBackground(Long... id) {
            return cardsDoneDao.getAllCardsDoneOfUser(id[0]);
        }
    }
}
