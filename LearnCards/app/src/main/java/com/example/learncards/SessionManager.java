package com.example.learncards;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.learncards.Activities.MainActivity;

import java.util.HashMap;

public class SessionManager {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    private static final String NAME = "NAME";
    private static final String ID = "ID";
    private static final String EMAIL = "EMAIL";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String email, String name, long userID) {
        editor.putString(EMAIL, email);
        editor.putString(NAME, name);
        editor.putLong(ID, userID);

        editor.putBoolean(LOGIN, true);
        editor.apply();
    }

    public boolean isLogin() {
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void checkLogin() {
        if (!this.isLogin()){
            Intent i = new Intent(context, MainActivity.class);
            context.startActivity(i);
        }
    }

    public HashMap<String, Object> getUser() {
        HashMap<String, Object> user = new HashMap<>();
        user.put(NAME, sharedPreferences.getString(NAME, null));
        user.put(EMAIL, sharedPreferences.getString(EMAIL, null));
        user.put(ID, sharedPreferences.getLong(ID, 0));
        return user;
    }

    public void logout() {
        editor.clear();
        editor.commit();
    }
}
