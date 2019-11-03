package com.example.learncards.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.learncards.Entities.CardsDone;
import com.example.learncards.Repositories.CardsDoneRepository;

import java.util.List;

public class CardsDoneViewModel extends AndroidViewModel {

    private CardsDoneRepository repository;
    private List<CardsDone> allCardsDone;

    public CardsDoneViewModel(@NonNull Application application, long userId){
        super(application);
        repository = new CardsDoneRepository(application, userId);
        allCardsDone = repository.getCardsDones();
    }
}
