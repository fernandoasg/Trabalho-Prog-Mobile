package com.example.learncards.Repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.example.learncards.Dao.CardDao;
import com.example.learncards.Database.AppDatabase;
import com.example.learncards.Entities.Card;
import com.example.learncards.Entities.CardWithQuestions;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class CardRepository {

    private CardDao cardDao;
    private List<Card> allCards;

    public CardRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        cardDao = db.cardDao();
        try {
            allCards = new GetAllCardsTask().execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Card> getAllCards() {
        return allCards;
    }

    public List<Card> getUserCards(long userID) {
        try {
            return new GetUserCardsTask().execute(userID).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CardWithQuestions> getCardsFromMySubjects(long subjectFk){
        try{
            return new GetAllCardsOfSubject().execute(subjectFk).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public CardWithQuestions getCard(long cardId){
        try{
            return new GetCard().execute(cardId).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private class GetAllCardsTask extends AsyncTask<Void, Void, List<Card>> {
        @Override
        protected List<Card> doInBackground(Void... url) {
            return cardDao.getAllCards();
        }
    }

    private class GetUserCardsTask extends AsyncTask<Long, Void, List<Card>> {
        @Override
        protected List<Card> doInBackground(Long... longs) {
            // longs[0] = userID
            return cardDao.getUserCards(longs[0]);
        }
    }

    private class GetAllCardsOfSubject extends AsyncTask<Long, Void, List<CardWithQuestions>>{
        @Override
        protected  List<CardWithQuestions> doInBackground(Long... longs){
            return cardDao.loadAllCardsOfSubject(longs[0]);
        }
    }

    private class GetCard extends  AsyncTask<Long, Void, CardWithQuestions>{
        @Override
        protected CardWithQuestions doInBackground(Long... longs){
            return cardDao.loadCard(longs[0]);
        }
    }

}
