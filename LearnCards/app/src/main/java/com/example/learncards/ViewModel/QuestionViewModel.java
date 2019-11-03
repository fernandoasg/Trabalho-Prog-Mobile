package com.example.learncards.ViewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.annotation.NonNull;

import com.example.learncards.Entities.Question;
import com.example.learncards.Repositories.QuestionRepository;

import java.util.List;

public class QuestionViewModel extends AndroidViewModel {

    private QuestionRepository repository;
    private List<Question> allQuestions;

    public QuestionViewModel(@NonNull Application application){
        super(application);
        repository = new QuestionRepository(application);
        allQuestions = repository.getAllQuestions();
    }
}
