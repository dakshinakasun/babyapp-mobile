package com.example.babyapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.babyapp.Adapters.PostAdapter;
import com.example.babyapp.Fragment.DailyTabFragment;
import com.example.babyapp.Fragment.LoginTabFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;
    
    TabLayout tabLayout;
    ViewPager viewPager;

    ProgressBar progressBar;

    private String roll;
    private String pDate;
    private String bDate;
    private String cDate;
    private int dif;

    private long days;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

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
        bottomNavigationView.setSelectedItemId(R.id.dashboard);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.article:
                        startActivity(new Intent(getApplicationContext(),Article.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.calculator:
                        startActivity(new Intent(getApplicationContext(),Calculator.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.notification:
                        startActivity(new Intent(getApplicationContext(),Notification.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.dashboard:

                }
                return false;
            }
        });

        //Dashboard
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_page);

        //Dashboard Implementation
        tabLayout.addTab(tabLayout.newTab().setText("Day"));
        tabLayout.addTab(tabLayout.newTab().setText("Week"));
        tabLayout.addTab(tabLayout.newTab().setText("Month"));

        tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);

        final PostAdapter adapter = new PostAdapter(getSupportFragmentManager(),this,tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        SharedPreferences sharedPreferences = getSharedPreferences(LoginTabFragment.MyPREFERENCES, Context.MODE_PRIVATE);
        roll = sharedPreferences.getString("rollKey", null);
        pDate = sharedPreferences.getString("pDateKey", null);
        bDate = sharedPreferences.getString("bDateKey", null);

        //get current date
        cDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);


        if (roll.equals("admin")){

            //Progress Bar
            progressBar = findViewById(R.id.progressBar);
            progressBar.setMax(280); // 100 maximum value for the progress value
            progressBar.setProgress(280); // 50 default progress value for the progress bar
        }

        if (!pDate.equals("null")){

            LocalDate date1 = LocalDate.parse(pDate, dateTimeFormatter);
//            LocalDate date2 = LocalDate.parse(bDate, dateTimeFormatter);
            LocalDate date3 = LocalDate.parse(cDate, dateTimeFormatter);

            LocalDate date = LocalDate.from(date1);

            days = date.until( date3, ChronoUnit.DAYS );
            date = date.plusDays( days );

            dif = Integer.parseInt(String.valueOf(days + 1));

            //Progress Bar
            progressBar = findViewById(R.id.progressBar);
            progressBar.setMax(280); // 100 maximum value for the progress value
            progressBar.setProgress(dif); // 50 default progress value for the progress bar

        }

        else if (!bDate.equals("null")){

//            LocalDate date1 = LocalDate.parse(pDate, dateTimeFormatter);
            LocalDate date2 = LocalDate.parse(bDate, dateTimeFormatter);
            LocalDate date3 = LocalDate.parse(cDate, dateTimeFormatter);

            LocalDate date = LocalDate.from(date2);

            days = date.until( date3, ChronoUnit.DAYS );
            date = date.plusDays( days );

            dif = Integer.parseInt(String.valueOf(days + 1));

            //Progress Bar
            progressBar = findViewById(R.id.progressBar);
            progressBar.setMax(280); // 280 maximum value for the progress value
            progressBar.setProgress(dif); // default progress value for the progress bar

        }


        System.out.println(dif);




    }
    //progress bar function

    //side nav bar
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_Dashboard:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Message()).commit();
                Intent intentDash = new Intent(Dashboard.this, Dashboard.class);
                intentDash.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentDash);
                break;

            case R.id.nav_article:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Chat()).commit();
                Intent intentArt = new Intent(Dashboard.this, Article.class);
                intentArt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentArt);
                break;

            case R.id.nav_calculator:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Profile()).commit();
                Intent intentCal = new Intent(Dashboard.this, Calculator.class);
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


                Intent intent = new Intent(Dashboard.this, MainActivity.class);
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