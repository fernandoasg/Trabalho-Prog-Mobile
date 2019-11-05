package com.example.learncards.Database;

import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
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
import com.example.learncards.Entities.UserSubject;

@Database(entities = {
            User.class,
            Subject.class,
            Card.class,
            Question.class,
            CardsDone.class,
            UserSubject.class}, exportSchema = false, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract UserDao userDao();
    public abstract SubjectDao subjectDao();
    public abstract QuestionDao questioDao();
    public abstract CardDao cardDao();
    public abstract CardsDoneDao cardsDoneDao();


    public static synchronized AppDatabase getInstance(Context context){

        if(instance == null){
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
            subjectDao.insert(new Subject("Biologia", "Sistema Cardiovascular"));
            subjectDao.insert(new Subject("Biologia", "Espécies"));

            subjectDao.insert(new Subject("Física", "Leis de Newton"));
            subjectDao.insert(new Subject("Física", "Resistencia do Ar"));

            subjectDao.insert(new Subject("Quimica", "Reações quimicas"));
            subjectDao.insert(new Subject("Quimica", "Atomos"));

            subjectDao.insert(new Subject("Matemática", "Equações de 2nd grau"));
            subjectDao.insert(new Subject("Matemática", "Algebra Linear"));

            subjectDao.insert(new Subject("Filosofia", "Maconha I"));
            subjectDao.insert(new Subject("Filosofia", "Marxismo II"));
            return null;
        }
    }
}
