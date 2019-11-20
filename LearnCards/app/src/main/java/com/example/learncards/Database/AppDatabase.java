package com.example.learncards.Database;

import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.example.learncards.Dao.CardDao;
import com.example.learncards.Dao.CardsDoneDao;
import com.example.learncards.Dao.QuestionDao;
import com.example.learncards.Dao.SubjectDao;
import com.example.learncards.Dao.UserDao;
import com.example.learncards.Dao.UserSubjectDao;
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
        UserSubject.class
}, exportSchema = false, version = 3)

public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract UserDao userDao();

    public abstract SubjectDao subjectDao();

    public abstract QuestionDao questioDao();

    public abstract CardDao cardDao();

    public abstract CardsDoneDao cardsDoneDao();

    public abstract UserSubjectDao userSubjectDao();

    public static synchronized AppDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "app_database.db")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback).build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDao userDao;
        private SubjectDao subjectDao;
        private CardDao cardDao;
        private QuestionDao questionDao;

        private PopulateDbAsyncTask(AppDatabase db) {
            subjectDao = db.subjectDao();
            userDao = db.userDao();
            cardDao = db.cardDao();
            questionDao = db.questioDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.insert(new User("teste", "123@gmail.com", "123"));
            subjectDao.insert(new Subject("Programação para Dispositivos Móveis", "Notificação"));

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

            cardDao.insert(new Card(1, 1, "O que é uma Notificação",
                    "Uma notificação é uma mensagem que pode ser exibida ao usuário fora da IU normal do aplicativo\n" +
                            "A notificação aparece primeiro como um ícone na área de notificação\n" +
                            "Para ver os detalhes da notificação, o usuário abre a gaveta de notificação\n" +
                            "A área de notificação e a gaveta de notificação podem ser visualizadas pelo usuário a qualquer momento\n",
                    "Podemos uma notificação para lembrar os usuários de fazer algo, para avisar que algo aconteceu, como o recebimento de um e-mail ou uma promoção ativa no aplicativo", 0));
            cardDao.insert(new Card(2, 1, "Ações de Notificação",
                    "Deve-se adicionar pelo menos uma ação à notificação\n" +
                            "Uma ação permite que os usuários direcionem-se diretamente da notificação para uma Activity\n" +
                            "Onde podem visualizar um ou mais eventos ou realizar outros trabalhos\n" +
                            "Deve-se sempre definir a ção que será ativada quando o usuário clicar na notificação\n" +
                            "Geralmente, esta ação abre uma Activity no aplicativo\n",
                    "Caso o usuário receba uma notificação de um email que foi recebido, a ação de clicar na notificação pode, por exemplo, abrir o aplicativo de email na Activity em que é mostrado o email com mais detalhes", 0));
            cardDao.insert(new Card(3, 2, "Sistema Cardiovascular ENEM", "Perguntas sobre o sistema cardiovascular p/ o ENEM", "contexto ?", 0));
            cardDao.insert(new Card(4, 3, "Espécies reino animal", "Perguntas espécies do reino animal", "contexto ?", 0));
            cardDao.insert(new Card(5, 4, "Gravidade questões práticas", "Perguntas sobre a lei de newton mais famosa !", "contexto ?", 0));
            cardDao.insert(new Card(6, 5, "Resistencia do Ar, ENEM", "Perguntas sobre a resistencia do ar p/ o ENEM", "contexto ?", 0));

            questionDao.insert(new Question(1, 1, "O que é uma Notificação?",
                    "É uma mensagem que pode ser exibida ao usuário fora da IU do aplicativo",
                    "É um texto sms que o usuário recebe",
                    "É um vídeo informativo que o usuário pode receber a qualquer momento",
                    "É uma forma do usuário desbloquear o celular",
                    "É uma mensagem que o usuário só pode acessar entrando nos aplicativos",
                    'a'));
            questionDao.insert(new Question(2, 1, "Podemos utilizar notificações para as seguintes ações, exceto:",
                    "Avisar a chegada de um e-mail",
                    "Mostrar uma mensagem de recordação para o usuário",
                    "Mostrar que o motorista do Uber está chegando",
                    "Tirar foto de forma mais rápida",
                    "Mostrar as últimas mensagens recebidas do WhatsApp",
                    'd'));

            return null;
        }
    }
}
