package com.example.learncards.Activities;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import com.example.learncards.Database.AppDatabase;
import com.example.learncards.R;
import com.example.learncards.SessionManager;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button openLoginButton = findViewById(R.id.openLoginButton);
        Button openRegisterButton = findViewById(R.id.openRegisterButton);

        checkLogin();

        openLoginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        openRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        checkLogin();
        super.onResume();
    }

    public void checkLogin(){
        SessionManager sessionManager = new SessionManager(getApplicationContext());
        if (sessionManager.isLogin()) {
            Intent i = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(i);
            finish();
        }
    }
}
