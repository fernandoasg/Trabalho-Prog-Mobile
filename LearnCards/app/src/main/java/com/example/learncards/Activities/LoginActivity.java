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
                String email = emailText.getText().toString();
                String senha = passwordText.getText().toString();
                if (email != null && senha != null) {
                    errorsText.setText(null);

                    List<String> loginESenha = new ArrayList<>();
                    loginESenha.add(email);
                    loginESenha.add(senha);

                    try {
                        String result = new LoginTask().execute(loginESenha).get();

                        if(result.equals("logado")){
                            Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(i);
                        }else{
                            errorsText.setText("Email e/ou senha incorreto(s)!");
                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    errorsText.setText("Digite o email e a senha.");
                }
            }
        });
    }

    class LoginTask extends AsyncTask<List<String>, Void, String> {

        @Override
        protected String doInBackground(List<String>... list) {

            AppDatabase mydb = AppDatabase.getInstance(LearnCards.getAppContext());

            List<User> users = mydb.userDao().getAllUsers();
            String email = list[0].get(0);
            String senha = list[0].get(1);
            System.out.println(email + "  " + senha);
            for (User user : users) {
                if (user.getEmail().equals(email) && user.getPassword().equals(senha)) {

                    SessionManager sessionManager = new SessionManager(getApplicationContext());
                    sessionManager.createSession(user.getEmail(), user.getName(), user.getId());

                    return "logado";
                }
            }

            return "login-invalido";
        }
    }
}