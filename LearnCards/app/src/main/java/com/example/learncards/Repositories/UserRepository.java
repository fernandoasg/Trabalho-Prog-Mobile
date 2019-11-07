package com.example.learncards.Repositories;

import android.app.Application;
import androidx.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.learncards.Dao.UserDao;
import com.example.learncards.Database.AppDatabase;
import com.example.learncards.Entities.Subject;
import com.example.learncards.Entities.User;
import com.example.learncards.Entities.UserSubject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserRepository {
    private UserDao userDao;
    private List<User> allUsers;
    private List<Subject> mySubjects;

    public UserRepository(Application application){
        AppDatabase database = AppDatabase.getInstance(application);
        userDao = database.userDao();
        allUsers = userDao.getAllUsers();
    }

    public void insert(User user){
        new InsertUserAsyncTask(userDao).execute(user);
    }

    public void update(User user){
        new UpdateUserAsyncTask(userDao).execute(user);
    }

    public void delete(User user){
        new DeleteUserAsyncTask(userDao).execute(user);
    }

    public void deleteAllUsers(){
        new DeleteAllUserAsyncTask(userDao).execute();
    }

    public List<Subject> getAllMySubjects(long userId){

        List<Subject> mySubjects = new ArrayList<>();
        try {
            mySubjects = new GetAllSubjectsFromUser(userId).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return mySubjects;
    }

    public List<User> getAllUsers(){
        return  allUsers;
    }

    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;
        private InsertUserAsyncTask(UserDao userDao){
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;
        private UpdateUserAsyncTask(UserDao userDao){
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.update(users[0]);
            return null;
        }
    }

    private static class DeleteUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;
        private DeleteUserAsyncTask(UserDao userDao){
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.delete(users[0]);
            return null;
        }
    }

    private static class DeleteAllUserAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDao userDao;
        private DeleteAllUserAsyncTask(UserDao userDao){
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.deleteAllUsers();
            return null;
        }
    }

    private class GetAllSubjectsFromUser extends AsyncTask<Void, Void, List<Subject>> {
        private Long userId;
        private GetAllSubjectsFromUser(Long userId){
            this.userId = userId;
        }

        @Override
        protected List<Subject> doInBackground(Void... url){
            return userDao.getAllSubjectsFromUser(userId);
        }
    }
}
