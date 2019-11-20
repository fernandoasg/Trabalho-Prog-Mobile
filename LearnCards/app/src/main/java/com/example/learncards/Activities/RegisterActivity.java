package com.example.learncards.Activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.learncards.Database.AppDatabase;
import com.example.learncards.Entities.User;
import com.example.learncards.R;
import com.example.learncards.SessionManager;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText editNome;
    private EditText editEmail;
    private EditText editSenha;
    private EditText editConfirmarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editNome = findViewById(R.id.editNome);
        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);
        editConfirmarSenha = findViewById(R.id.editSenhaConf);

        Button registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                RegisterTaskParam dados = new RegisterTaskParam(editNome.getText().toString(),
                        editEmail.getText().toString(),
                        editSenha.getText().toString(),
                        editConfirmarSenha.getText().toString()
                        );


                if(!isValidEmail(dados.email)){

                    AlertDialog alerta;

                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setTitle("Erro ao cadastrar");
                    builder.setMessage("Email inválido  ");

                    alerta = builder.create();
                    alerta.show();
                }
                else if(!dados.senha.equals(dados.confirmarSenha)){
                    AlertDialog alerta;

                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setTitle("Erro ao cadastrar");
                    builder.setMessage("As senhas não coincidem, tente novamente");

                    alerta = builder.create();
                    alerta.show();
                }
                else {
                    new RegisterTask().execute(dados);
                }
            }
        });
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    class RegisterTask extends AsyncTask<RegisterTaskParam, Void, Void> {

        @Override
        protected Void doInBackground(RegisterTaskParam... param) {

            SessionManager sessionManager = new SessionManager(getApplicationContext());


            AppDatabase appDatabase = AppDatabase.getInstance(getApplicationContext());
            
            RegisterTaskParam dados = param[0];
            User user = new User(
                    dados.nome,
                    dados.email,
                    dados.senha
                );

            appDatabase.userDao().insert(user);
            sessionManager.createSession(user.getEmail(), user.getName(), user.getId());

            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
            finish();

            return null;
        }
    }


    /**
     * Classe utilizada exclusivamente para a Task de registro
     */
    private static class RegisterTaskParam {
        String nome;
        String email;
        String senha;
        String confirmarSenha;

        public RegisterTaskParam(String nome, String email, String senha, String confirmarSenha) {
            this.nome = nome;
            this.email = email;
            this.senha = senha;
            this.confirmarSenha = confirmarSenha;
        }
    }
}
