package com.example.learncards.ViewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.annotation.NonNull;

import com.example.learncards.Entities.Card;
import com.example.learncards.Repositories.CardRepository;

import java.util.List;

public class CardViewModel extends AndroidViewModel {

    private List<Card> allCards;

    public CardViewModel(@NonNull Application application){
        super(application);
        CardRepository repository = new CardRepository(application);
        allCards = repository.getAllCards();
    }

    public List<Card> getAllCards() {
        return allCards;
    }
}
