package com.example.learncards.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.learncards.Database.AppDatabase;
import com.example.learncards.Entities.User;
import com.example.learncards.R;
import com.example.learncards.SessionManager;

public class RegisterActivity extends AppCompatActivity {

    private EditText editNome;
    private EditText editEmail;
    private EditText editSenha;
    private EditText editSenhaConf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editNome = findViewById(R.id.editNome);
        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);
        editSenhaConf = findViewById(R.id.editSenhaConf);

        Button registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new RegisterTask().execute();
            }
        });
    }

    class RegisterTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            SessionManager sessionManager = new SessionManager(getApplicationContext());

            AppDatabase appDatabase = AppDatabase.getInstance(getApplicationContext());

            if (editSenha.getText().toString().equals(editSenhaConf.getText().toString())) {

                User user = new User(
                        editNome.getText().toString(),
                        editEmail.getText().toString(),
                        editSenha.getText().toString()
                );

                appDatabase.userDao().insert(user);
                sessionManager.createSession(user.getEmail(), user.getName(), user.getId());

                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                finish();
            }
            return null;
        }
    }
}
