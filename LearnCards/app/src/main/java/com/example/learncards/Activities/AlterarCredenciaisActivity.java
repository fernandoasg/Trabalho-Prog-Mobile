package com.example.learncards.Activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learncards.Database.AppDatabase;
import com.example.learncards.EmailValidator;
import com.example.learncards.Entities.User;
import com.example.learncards.R;
import com.example.learncards.SessionManager;


public class AlterarCredenciaisActivity extends AppCompatActivity {

    private EditText editNome;
    private EditText editEmail;
    private EditText editSenha;
    private EditText editConfirmarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_credenciais);

        editNome = findViewById(R.id.editNomeAlt);
        editEmail = findViewById(R.id.editEmailAlt);
        editSenha = findViewById(R.id.editSenhaAlt);
        editConfirmarSenha = findViewById(R.id.editSenhaConfAlt);

        SessionManager sessionManager = new SessionManager(this);
        long userID = (long) sessionManager.getUser().get("ID");

        Button salvarAlteracoesButton = findViewById(R.id.buttonSalvarAlteracoes);

        salvarAlteracoesButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SessionManager sessionManager = new SessionManager(AlterarCredenciaisActivity.this);
                long userID = (long) sessionManager.getUser().get("ID");

                UpdateUserTaskParam dados = new UpdateUserTaskParam(
                        userID,
                        editNome.getText().toString(),
                        editEmail.getText().toString(),
                        editSenha.getText().toString(),
                        editConfirmarSenha.getText().toString()
                );

                if (!EmailValidator.isValidEmail(dados.email)) {

                    AlertDialog alerta;

                    AlertDialog.Builder builder = new AlertDialog.Builder(AlterarCredenciaisActivity.this);
                    builder.setTitle("Erro ao cadastrar");
                    builder.setMessage("Email inválido  ");

                    alerta = builder.create();
                    alerta.show();
                } else if (!dados.senha.equals(dados.confirmarSenha)) {
                    AlertDialog alerta;

                    AlertDialog.Builder builder = new AlertDialog.Builder(AlterarCredenciaisActivity.this);
                    builder.setTitle("Erro ao cadastrar");
                    builder.setMessage("As senhas não coincidem, tente novamente");

                    alerta = builder.create();
                    alerta.show();
                } else {
                    new AlterarCredenciaisActivity.UpdateUserTask().execute(dados);
                }
            }
        });
    }

    class UpdateUserTask extends AsyncTask<UpdateUserTaskParam, Void, Void> {

        @Override
        protected Void doInBackground(UpdateUserTaskParam... params) {

            AppDatabase appDatabase = AppDatabase.getInstance(getApplicationContext());

            UpdateUserTaskParam dados = params[0];

            User user = new User(
                    dados.nome,
                    dados.email,
                    dados.senha,
                    dados.userID
            );

            appDatabase.userDao().update(user);


            SessionManager sessionManager = new SessionManager(getApplicationContext());
            sessionManager.createSession(dados.email, dados.nome, dados.userID);

            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);

            finish();

            return null;
        }
    }


    /**
     * Classe utilizada exclusivamente para a Task de update de usuario
     */
    private static class UpdateUserTaskParam {
        Long userID;
        String nome;
        String email;
        String senha;
        String confirmarSenha;

        public UpdateUserTaskParam(Long userID, String nome, String email, String senha, String confirmarSenha) {
            this.userID = userID;
            this.nome = nome;
            this.email = email;
            this.senha = senha;
            this.confirmarSenha = confirmarSenha;
        }
    }
}