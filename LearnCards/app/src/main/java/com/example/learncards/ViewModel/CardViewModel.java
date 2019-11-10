package com.example.learncards.ViewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.annotation.NonNull;

import com.example.learncards.Entities.Card;
import com.example.learncards.Entities.CardWithQuestions;
import com.example.learncards.Repositories.CardRepository;

import java.util.List;

public class CardViewModel extends AndroidViewModel {

    private CardRepository repository;
    private List<Card> allCards;
    private List<Card> userCards;

    public CardViewModel(@NonNull Application application){
        super(application);
        repository = new CardRepository(application);
        allCards = repository.getAllCards();
    }

    public List<Card> getAllCards() {
        return allCards;
    }

    public List<Card> getUserCards(long userID) {
        return repository.getUserCards(userID);
    }

    public List<CardWithQuestions>  getCardsFromMySubjects(long subjectId){
        return repository.getCardsFromMySubjects(subjectId);
    }

    public CardWithQuestions getCard(long cardId){
        return repository.getCard(cardId);
    }
}
