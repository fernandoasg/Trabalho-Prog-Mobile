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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {

    private EditText emailText;
    private EditText passwordText;

    private TextView errorsText;
    private AppDatabase mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);

        errorsText = findViewById(R.id.textErrors);

        mydb = AppDatabase.getInstance(LearnCards.getAppContext());

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = emailText.getText().toString();
                String senha = passwordText.getText().toString();
                if (!email.equals("") && !senha.equals("")) {
                    errorsText.setText(null);

                    List<String> loginESenha = new ArrayList<>();
                    loginESenha.add(email);
                    loginESenha.add(senha);

                    try {
                        if(new LoginTask().execute(loginESenha).get()){
                            Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                        }else{
                            errorsText.setText("Email e/ou senha incorreto(s)!");
                        }
                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    errorsText.setText("Digite o email e a senha.");
                }
            }
        });
    }

    class LoginTask extends AsyncTask<List<String>, Void, Boolean> {

        @Override
        protected Boolean doInBackground(List<String>... list) {

            List<User> users = mydb.userDao().getAllUsers();
            String email = list[0].get(0);
            String senha = list[0].get(1);
            for (User user : users) {
                if (user.getEmail().equals(email) && user.getPassword().equals(senha)) {

                    SessionManager sessionManager = new SessionManager(getApplicationContext());
                    sessionManager.createSession(user.getEmail(), user.getName(), user.getId());
                    return true;
                }
            }
            return false;
        }
    }
}