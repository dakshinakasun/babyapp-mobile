package com.example.babyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class TestPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().hide();
        setContentView(R.layout.activity_test_page);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

//        if (savedInstanceState == null){
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Message()).commit();
//            navigationView.setCheckedItem(R.id.nav_message);
//        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_Dashboard:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Message()).commit();
                Intent intentDash = new Intent(TestPage.this, Dashboard.class);
                intentDash.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentDash);
                break;

            case R.id.nav_article:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Chat()).commit();
                Intent intentArt = new Intent(TestPage.this, Article.class);
                intentArt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentArt);
                break;

            case R.id.nav_calculator:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Profile()).commit();
                Intent intentCal = new Intent(TestPage.this, Calculator.class);
                intentCal.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentCal);
                break;

            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_send:
                Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();
                break;
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