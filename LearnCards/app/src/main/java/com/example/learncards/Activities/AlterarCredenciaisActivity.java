package com.example.learncards.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learncards.Database.AppDatabase;
import com.example.learncards.Entities.User;
import com.example.learncards.R;
import com.example.learncards.SessionManager;

public class AlterarCredenciaisActivity extends AppCompatActivity {

    private EditText editNome;
    private EditText editEmail;
    private EditText editSenha;
    private EditText editSenhaConf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_credenciais);

        editNome = findViewById(R.id.editNomeAlt);
        editEmail = findViewById(R.id.editEmailAlt);
        editSenha = findViewById(R.id.editSenhaAlt);
        editSenhaConf = findViewById(R.id.editSenhaConfAlt);

        Button salvarAlteracoesButton = findViewById(R.id.buttonSalvarAlteracoes);

        salvarAlteracoesButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new AlterarCredenciaisActivity.AlterarCredenciaisTask().execute();
            }
        });
    }

    class AlterarCredenciaisTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            AppDatabase appDatabase = AppDatabase.getInstance(getApplicationContext());

            SessionManager sessionManager = new SessionManager(getApplicationContext());
            long userID = (long) sessionManager.getUser().get("ID");

            if (editSenha.getText().toString().equals(editSenhaConf.getText().toString())) {

                // TODO VER COMO ATUALIZAR O USUARIO USANDO O ROOM
                User user = new User(
                        editNome.getText().toString(),
                        editEmail.getText().toString(),
                        editSenha.getText().toString()
                );

                appDatabase.userDao().update(user);

                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);

                Log.i("SADASDASDASASD", "doInBackground: --------------------------");
                finish();
            }
            return null;
        }
    }
}