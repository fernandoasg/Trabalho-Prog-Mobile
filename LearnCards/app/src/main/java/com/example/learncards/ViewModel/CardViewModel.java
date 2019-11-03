package com.example.learncards.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.learncards.Entities.Card;
import com.example.learncards.Repositories.CardRepository;

import java.util.List;

public class CardViewModel extends AndroidViewModel {

    private CardRepository repository;
    private List<Card> allCards;

    public CardViewModel(@NonNull Application application){
        super(application);
        repository = new CardRepository(application);
        allCards = repository.getAllCards();
    }
}
