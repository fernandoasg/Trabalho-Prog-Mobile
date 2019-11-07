package com.example.learncards.Activities;

import android.app.Application;
import android.content.Context;

/**
 * Classe criada para acessar o contexto da aplicação de métodos estaticos
 * TODO UTILIZAR ISSO
 */
public class LearnCards extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        LearnCards.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return LearnCards.context;
    }

}
