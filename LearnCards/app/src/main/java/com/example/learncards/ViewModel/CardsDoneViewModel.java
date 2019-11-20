package com.example.learncards.ViewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.annotation.NonNull;

import com.example.learncards.Entities.CardsDone;
import com.example.learncards.Repositories.CardsDoneRepository;
import com.example.learncards.SessionManager;

import java.util.List;

public class CardsDoneViewModel extends AndroidViewModel {

    private CardsDoneRepository repository;
    private List<CardsDone> allCardsDone;

    public CardsDoneViewModel(@NonNull Application application){
        super(application);
        SessionManager sessionManager = new SessionManager(getApplication().getApplicationContext());
        long userID = (long) sessionManager.getUser().get("ID");
        repository = new CardsDoneRepository(application, userID);
        allCardsDone = repository.getCardsDones();
    }

    public void saveCardDone(CardsDone cardsDone){
        repository.insert(cardsDone);
    }
}
