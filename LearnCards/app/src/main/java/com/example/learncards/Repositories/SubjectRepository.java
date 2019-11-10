package com.example.learncards.Repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.example.learncards.Dao.SubjectDao;
import com.example.learncards.Database.AppDatabase;
import com.example.learncards.Entities.Subject;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class SubjectRepository {

    private SubjectDao subjectDao;

    public SubjectRepository(Application application){
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        subjectDao = appDatabase.subjectDao();
    }

    public List<Subject> getAllSubjects() {
        try {
            return new GetAllSubjectsTask().execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Subject> getAllNonSelectedSubjects(long userID) {
        try {
            return new GetAllNonSelectedSubjectsTask().execute(userID).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Subject> getAllUserSubjects(long userID){
        try{
            return new GetAllUserSubjectsTask().execute(userID).get();
        }catch(ExecutionException | InterruptedException e){
            e.printStackTrace();
        }
        return null;
    }

    private class GetAllSubjectsTask extends AsyncTask<Void, Void, List<Subject>> {
        @Override
        protected List<Subject> doInBackground(Void... url){
            return subjectDao.getAllSubjects();
        }
    }

    private class GetAllNonSelectedSubjectsTask extends AsyncTask<Long, Void, List<Subject>> {
        @Override
        protected List<Subject> doInBackground(Long... userID){
            return subjectDao.getAllSubjectsNotSelectedByUser(userID[0]);
        }
    }

    private class GetAllUserSubjectsTask extends AsyncTask<Long, Void, List<Subject>> {
        @Override
        protected List<Subject> doInBackground(Long... userID){
            return subjectDao.getAllSubjectsSelectedByUser(userID[0]);
        }
    }
}
