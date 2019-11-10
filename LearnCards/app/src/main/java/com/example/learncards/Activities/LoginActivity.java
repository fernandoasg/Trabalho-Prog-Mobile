package com.example.learncards.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.learncards.Database.AppDatabase;
import com.example.learncards.Entities.User;
import com.example.learncards.R;
import com.example.learncards.SessionManager;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private EditText emailText;
    private EditText passwordText;

    private TextView errorsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);

        errorsText = findViewById(R.id.textErrors);

        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginTask loginTask = new LoginTask();
                loginTask.execute();
            }
        });
    }

    class LoginTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            AppDatabase mydb = AppDatabase.getInstance(LearnCards.getAppContext());

            List<User> users = mydb.userDao().getAllUsers();

            for (User user : users) {
                if (user.getEmail().equals(emailText.getText().toString()) && user.getPassword().equals(passwordText.getText().toString())) {
                    errorsText.setText(null);

                    SessionManager sessionManager = new SessionManager(getApplicationContext());
                    sessionManager.createSession(user.getEmail(), user.getName(), user.getId());

                    Intent intent = new Intent("finish_activity");
                    sendBroadcast(intent);

                    Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(i);

                    finish();
                    return null;
                }
            }

            errorsText.setText("Usuário ou senha incorreto !");

            return null;
        }
    }
}