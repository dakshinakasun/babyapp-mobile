package com.example.babyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.babyapp.Fragment.LoginTabFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class Calculator extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        //Side navigation bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Initialize And Assign Variables
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.calculator);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.article:
                        startActivity(new Intent(getApplicationContext(),Article.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(),Dashboard.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.notification:
                        startActivity(new Intent(getApplicationContext(),Notification.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.calculator:

                }
                return false;
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_Dashboard:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Message()).commit();
                Intent intentDash = new Intent(Calculator.this, Dashboard.class);
                intentDash.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentDash);
                break;

            case R.id.nav_article:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Chat()).commit();
                Intent intentArt = new Intent(Calculator.this, Article.class);
                intentArt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentArt);
                break;

            case R.id.nav_calculator:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Profile()).commit();
                Intent intentCal = new Intent(Calculator.this, Calculator.class);
                intentCal.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentCal);
                break;

            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_send:
                Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_logout:

                SharedPreferences sharedPreferences = getSharedPreferences(LoginTabFragment.MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                Intent intent = new Intent(Calculator.this, MainActivity.class);
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else
            super.onBackPressed();
    }
}