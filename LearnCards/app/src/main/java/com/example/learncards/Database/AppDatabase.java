package com.example.learncards.Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.learncards.Dao.CardDao;
import com.example.learncards.Dao.CardsDoneDao;
import com.example.learncards.Dao.QuestionDao;
import com.example.learncards.Dao.SubjectDao;
import com.example.learncards.Dao.UserDao;
import com.example.learncards.Entities.Card;
import com.example.learncards.Entities.CardsDone;
import com.example.learncards.Entities.Question;
import com.example.learncards.Entities.Subject;
import com.example.learncards.Entities.User;

@Database(entities = {
            User.class,
            Subject.class,
            Card.class,
            Question.class,
            CardsDone.class}, exportSchema = false, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DBTAG = "[ DATABASE ]";

    private static AppDatabase instance;

    public abstract UserDao userDao();
    public abstract SubjectDao subjectDao();
    public abstract QuestionDao questioDao();
    public abstract CardDao cardDao();
    public abstract CardsDoneDao cardsDoneDao();


    public static synchronized AppDatabase getInstance(Context context){

        if(instance == null){
            Log.i(DBTAG, "CRIANDO INSTANCIA DB");

            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "app_database.db")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback).build();
        }

        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private UserDao userDao;
        private SubjectDao subjectDao;
        private PopulateDbAsyncTask(AppDatabase db){
            subjectDao = db.subjectDao();
            userDao = db.userDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.insert(new User("teste", "123@gmail.com", "123"));
            subjectDao.insert(new Subject("Teste", "Sub-teste1"));
            subjectDao.insert(new Subject("Teste", "Sub-teste2"));
            subjectDao.insert(new Subject("Teste", "Sub-teste3"));
            subjectDao.insert(new Subject("Teste", "Sub-teste4"));
            return null;
        }
    }
}
