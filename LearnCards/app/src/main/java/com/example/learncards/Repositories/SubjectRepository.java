package com.example.learncards.Repositories;

import android.app.Application;

import com.example.learncards.Dao.SubjectDao;
import com.example.learncards.Database.AppDatabase;
import com.example.learncards.Entities.Subject;

import java.util.List;

public class SubjectRepository {

    private SubjectDao subjectDao;
    private List<Subject> allSubjecties;

    public SubjectRepository(Application application){
        AppDatabase db = AppDatabase.getInstance(application);
        subjectDao = db.subjectDao();
        allSubjecties = db.subjectDao().getAllSubjects();
    }
}
