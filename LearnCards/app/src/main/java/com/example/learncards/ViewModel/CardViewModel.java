package com.example.learncards.ViewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.annotation.NonNull;

import com.example.learncards.Entities.CardWithQuestions;
import com.example.learncards.Repositories.CardRepository;

import java.util.List;

public class CardViewModel extends AndroidViewModel {

    private CardRepository repository;
    private List<CardWithQuestions> allCards;

    public CardViewModel(@NonNull Application application){
        super(application);
        repository = new CardRepository(application);
        allCards = repository.getAllCards();
    }
}
