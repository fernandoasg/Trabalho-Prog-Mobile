package com.example.learncards.Repositories;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.example.learncards.Dao.SubjectDao;
import com.example.learncards.Database.AppDatabase;
import com.example.learncards.Entities.Subject;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class SubjectRepository {

    private AppDatabase db;
    private SubjectDao subjectDao;
    private List<Subject> allSubjects;

    public SubjectRepository(Application application){
        db = AppDatabase.getInstance(application);
        subjectDao = db.subjectDao();
        try {
            allSubjects = new GetAllSubjectsAsyncTask().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Subject> getAllSubjecties() {
        return allSubjects;
    }

    private class GetAllSubjectsAsyncTask extends AsyncTask<Void, Void, List<Subject>> {
        @Override
        protected List<Subject> doInBackground(Void... url){
            return subjectDao.getAllSubjects();
        }
    }
}
