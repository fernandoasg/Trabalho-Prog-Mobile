package com.example.learncards.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;
import com.example.learncards.Database.AppDatabase;
import com.example.learncards.Entities.Subject;
import com.example.learncards.R;
import com.example.learncards.SessionManager;
import com.example.learncards.ViewModel.SubjectViewModel;
import com.google.android.material.navigation.NavigationView;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private List<Subject> userSubjects;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SubjectViewModel subjectViewModel = ViewModelProviders.of(this).get(SubjectViewModel.class);

        sessionManager = new SessionManager(getApplicationContext());
        long userID = (long) sessionManager.getUser().get("ID");

        userSubjects = subjectViewModel.getUserSubjects(userID);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.home_drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            if (userSubjects != null && !userSubjects.isEmpty()) {
                //TODO SUBJECT LIST FRAGMENT AQUI
                getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment_container,
                        new NoSubjectFragment()).commit();
            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment_container,
                        new NoSubjectFragment()).commit();
            }
            navigationView.setCheckedItem(R.id.nav_cards);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case R.id.nav_cards:
                if (getUserSubjects() != null && !getUserSubjects().isEmpty()) {
                    //TODO SUBJECT LIST FRAGMENT AQUI
                    getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment_container, new NoSubjectFragment()).commit();
                } else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment_container, new NoSubjectFragment()).commit();
                }
                drawer.closeDrawer(GravityCompat.START);
                break;

            case R.id.nav_user_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment_container,
                        new ProfileFragment()).commit();
                drawer.closeDrawer(GravityCompat.START);
                break;

            case R.id.nav_sair:
                sessionManager.logout();

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);

                finish();
                break;
        }

        return true;
    }

    private List<Subject> getUserSubjects() {
        try {
            return new GetUserSelectedSubjectsTask().execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    class GetUserSelectedSubjectsTask extends AsyncTask<Void, Void, List<Subject>> {

        @Override
        protected List<Subject> doInBackground(Void... voids) {
            SessionManager sessionManager = new SessionManager(getApplicationContext());
            AppDatabase appDatabase = AppDatabase.getInstance(LearnCards.getAppContext());

            return appDatabase.subjectDao().getAllSubjectsSelectedByUser(
                    (long) sessionManager.getUser().get("ID")
            );
        }
    }
}
