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

            userDao.insert(new User("Administrador", "admin@gmail.com", "123"));

//---------------------------- PROG PARA DISPOSITIVOS MÓVEIS, NOTIFICAÇÃO --------------------------

            subjectDao.insert(new Subject("Programação para Dispositivos Móveis", "Notificação"));

            cardDao.insert(new Card(1, 1, "O que é uma Notificação",
                    "Uma notificação é uma mensagem que pode ser exibida ao usuário fora da IU normal do aplicativo.\n" +
                            "A notificação aparece primeiro como um ícone na área de notificação.\n" +
                            "Para ver os detalhes da notificação, o usuário abre a gaveta de notificação.\n" +
                            "A área de notificação e a gaveta de notificação podem ser visualizadas pelo usuário a qualquer momento.\n",
                    "Podemos usar uma notificação para lembrar os usuários de fazer algo, para avisar que algo aconteceu, como " +
                            "o recebimento de um e-mail ou uma promoção ativa no aplicativo.", 0));

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

            cardDao.insert(new Card(2, 1, "Ações de Notificação",
                    "Deve-se adicionar pelo menos uma ação à notificação.\n" +
                            "Uma ação permite que os usuários direcionem-se diretamente da notificação para uma Activity.\n" +
                            "Onde podem visualizar um ou mais eventos ou realizar outros trabalhos.\n" +
                            "Deve-se sempre definir a ção que será ativada quando o usuário clicar na notificação.\n" +
                            "Geralmente, esta ação abre uma Activity no aplicativo.\n",
                    "Caso o usuário receba uma notificação de um email que foi recebido, a ação de clicar " +
                            "na notificação pode, por exemplo, abrir o aplicativo de email na Activity em que é " +
                            "mostrado o email com mais detalhes.", 0));

            questionDao.insert(new Question(3, 2, "O que é ação de notificação?",
                    "Os botões abaixo do corpo da notificação que disparão intents quando selecionados",
                    "A ação feita pelo aplicativo antes de disparar a notificação",
                    "A ação feita pelo aplicativo após disparar a notificação",
                    "Apenas os botões encapsulados no corpo da notificação",
                    "Apenas o intent disparado ao selecionar o botão encapsulado no corpo da notificação",
                    'a'));

            questionDao.insert(new Question(4, 2, "Podemos ações de notificações para as seguintes ações, exceto:",
                    "Disponibilizar várias ações para o usuário como abrir o app ou marcar uma mensagem como lida",
                    "Desinstalar o aplicativo",
                    "Abrir o aplicativo automaticamente, sem autorização do usuário",
                    "Reativar Threads em waiting ou blocked",
                    "Gabaritar prog mobile",
                    'a'));


//---------------------------- Biologia, Sistema Cardiovascular --------------------------

            subjectDao.insert(new Subject(2,"Biologia", "Sistema Cardiovascular"));

            cardDao.insert(new Card(3, 2, "Sistema Cardiovascular",
                    "O sistema cardiovascular ou sistema circulatório humano é responsável pela circulação do sangue, de modo a transportar os nutrientes e o oxigênio por todo o corpo. O Sistema Cardiovascular é formado pelos vasos sanguíneos e o coração.",
                    "Não há um exemplo para este card",
                    0));

            questionDao.insert(new Question(5, 3, "O sistema cardiovascular NÃO é responsavel por",
                    "Realizar a circulação do sangue",
                    "Transportar os nutrientes e o oxigênio por todo o corpo",
                    "Distribuição de nutrientes para as células e coleta de suas excretas metabólicas para serem eliminadas por órgãos excretores",
                    "Realizar a sensação de cócegas no pé",
                    "Transportar hormônios por todo o corpo",
                    'd'));

            questionDao.insert(new Question(6, 3, "Tudo a seguir faz parte do sistema cardiovascular, exceto:",
                    "Coração",
                    "Vasos sanguíneos",
                    "Vasos linfáticos",
                    "Sangue",
                    "Papilas gustativas",
                    'e'));

//---------------------------- Outros --------------------------

            subjectDao.insert(new Subject(3, "Biologia", "Espécies"));

            subjectDao.insert(new Subject(4, "Física", "Leis de Newton"));
            subjectDao.insert(new Subject(5, "Física", "Resistencia do Ar"));

            subjectDao.insert(new Subject(6, "Quimica", "Reações quimicas"));
            subjectDao.insert(new Subject(7, "Quimica", "Atomos"));

            subjectDao.insert(new Subject(8, "Matemática", "Equações de 2nd grau"));
            subjectDao.insert(new Subject(9, "Matemática", "Algebra Linear"));

            subjectDao.insert(new Subject(10, "Filosofia", "Comunismo I"));
            subjectDao.insert(new Subject(11, "Filosofia", "Marxismo II"));

            cardDao.insert(new Card(5, 3, "Espécies reino animal", "Perguntas espécies do reino animal", "contexto ?", 0));
            cardDao.insert(new Card(6, 4, "Gravidade questões práticas", "Perguntas sobre a lei de newton mais famosa !", "contexto ?", 0));
            cardDao.insert(new Card(7, 5, "Resistencia do Ar, ENEM", "Perguntas sobre a resistencia do ar p/ o ENEM", "contexto ?", 0));
            cardDao.insert(new Card(8, 6, "Reações quimicas, ENEM", "Perguntas sobre a Reações quimicas p/ o ENEM", "contexto ?", 0));
            cardDao.insert(new Card(9, 7, "Atomos, ENEM", "Perguntas sobre a Atomos p/ o ENEM", "contexto ?", 0));
            cardDao.insert(new Card(10, 8, "Equações de 2nd grau, ENEM", "Perguntas sobre a Equações de 2nd grau p/ o ENEM", "contexto ?", 0));
            cardDao.insert(new Card(11, 9, "Algebra Linear, ENEM", "Perguntas sobre a Algebra Linear p/ o ENEM", "contexto ?", 0));
            cardDao.insert(new Card(12, 10, "Comunismo I, ENEM", "Perguntas sobre a Comunismo I p/ o ENEM", "contexto ?", 0));
            cardDao.insert(new Card(13, 11, "Marxismo II, ENEM", "Perguntas sobre a Marxismo II p/ o ENEM", "contexto ?", 0));

            return null;
        }
    }
}
