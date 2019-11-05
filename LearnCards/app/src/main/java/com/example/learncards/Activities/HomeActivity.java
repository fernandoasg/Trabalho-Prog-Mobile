package com.example.learncards.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.learncards.R;
import com.example.learncards.SessionManager;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.home_drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null) {
            boolean hasSubjects = hasSubjects();
            if (hasSubjects) {
                getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment_container,
                        new CardsListFragment()).commit();
            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment_container,
                        new NoSubjectFragment()).commit();
            }
            navigationView.setCheckedItem(R.id.nav_cards);
        }
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_cards:
                boolean hasSubjects = hasSubjects();
                if(hasSubjects) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment_container,
                             new CardsListFragment()).commit();
                }else{
                    getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment_container,
                             new NoSubjectFragment()).commit();
                }
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_user_profile:
               getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment_container,
                            new ProfileFragment()).commit();
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_sair:
                SessionManager sessionManager = new SessionManager(getApplicationContext());
                sessionManager.logout();

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);

                finish();
                break;
        }

        return true;
    }

    // Retorna se o usuário tem algum assunto selecionado ou não
    private boolean hasSubjects(){
        return false;
    }
}
