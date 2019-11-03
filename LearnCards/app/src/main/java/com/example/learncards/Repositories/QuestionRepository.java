package com.example.learncards.Repositories;

import android.app.Application;

import com.example.learncards.Dao.QuestionDao;
import com.example.learncards.Database.AppDatabase;
import com.example.learncards.Entities.Question;

import java.util.List;

public class QuestionRepository {

    private QuestionDao questionDao;
    private List<Question> allQuestions;

    public QuestionRepository(Application application){
        AppDatabase db = AppDatabase.getInstance(application);
        questionDao = db.questioDao();
        allQuestions = questionDao.getAllQuestions();
    }

    public List<Question> getAllQuestions(){
        return allQuestions;
    }
}
