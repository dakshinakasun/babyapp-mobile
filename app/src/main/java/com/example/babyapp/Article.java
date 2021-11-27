package com.example.babyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.babyapp.Adapters.GeneralPostAdapter;
import com.example.babyapp.ExtraClasses.VolleySingleton;
import com.example.babyapp.Fragment.LoginTabFragment;
import com.example.babyapp.Pages.GeneralPosts;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Article extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;

    RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<GeneralPosts> generalPostsList;

    private String ImageURL = "https://test.bloomingmoms.lk/images/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

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
        bottomNavigationView.setSelectedItemId(R.id.article);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(),Dashboard.class));
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

                    case R.id.article:

                }
                return false;
            }
        });

        recyclerView = findViewById(R.id.PostsList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();

        generalPostsList = new ArrayList<>();

        fetchPosts();
    }

    private void fetchPosts() {

        String URL = "https://test.bloomingmoms.lk/api/blog";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        String title = jsonObject.getString("etitle");
                        String SiTitle = jsonObject.getString("stitle");
                        String highlight = jsonObject.getString("ehighlight");
                        String SiHighlight = jsonObject.getString("shighlight");
                        String description = jsonObject.getString("edescription");
                        String SiDescription = jsonObject.getString("sdescription");
                        String img = jsonObject.getString("image_path");
                        String image = ImageURL + img;

                        GeneralPosts generalPosts = new GeneralPosts(title, SiTitle, highlight, SiHighlight, description, SiDescription, image);
                        generalPostsList.add(generalPosts);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

//                    LoginTabFragment.super.getContext()

//                    DailyPostAdapter adapter = new DailyPostAdapter(MainActivity.this, dailyPostsList);
                    GeneralPostAdapter adapter = new GeneralPostAdapter(Article.this, generalPostsList);
                    recyclerView.setAdapter(adapter);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(Article.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_Dashboard:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Message()).commit();
                Intent intentDash = new Intent(Article.this, Dashboard.class);
                intentDash.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentDash);
                break;

            case R.id.nav_article:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Chat()).commit();
                Intent intentArt = new Intent(Article.this, Article.class);
                intentArt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentArt);
                break;

            case R.id.nav_calculator:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Profile()).commit();
                Intent intentCal = new Intent(Article.this, Calculator.class);
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

                Intent intent = new Intent(Article.this, MainActivity.class);
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