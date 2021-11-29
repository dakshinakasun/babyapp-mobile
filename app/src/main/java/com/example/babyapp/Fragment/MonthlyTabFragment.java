package com.example.babyapp.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.babyapp.Adapters.MonthlyPostAdapter;
import com.example.babyapp.ExtraClasses.VolleySingleton;
import com.example.babyapp.Pages.MonthlyPosts;
import com.example.babyapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MonthlyTabFragment extends Fragment {

    RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<MonthlyPosts> monthlyPostsList;

    private String ImageURL = "https://test.bloomingmoms.lk/images/";

    private String name;
    private String roll;
    private String pDate;
    private String bDate;
    private String cDate;

    private long months;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.monthly_tab_fragment, container, false);

        recyclerView = root.findViewById(R.id.PostsList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        requestQueue = VolleySingleton.getInstance(getContext()).getRequestQueue();

        monthlyPostsList = new ArrayList<>();

        fetchPosts();

        SharedPreferences sharedPreferences = getContext().getSharedPreferences(LoginTabFragment.MyPREFERENCES, Context.MODE_PRIVATE);
        name = sharedPreferences.getString("nameKey", null);
        roll = sharedPreferences.getString("rollKey", null);
        pDate = sharedPreferences.getString("pDateKey", null);
        bDate = sharedPreferences.getString("bDateKey", null);

        //get current date
        cDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

        if (!pDate.equals("null")){

            LocalDate date1 = LocalDate.parse(pDate, dateTimeFormatter);
//            LocalDate date2 = LocalDate.parse(bDate, dateTimeFormatter);
            LocalDate date3 = LocalDate.parse(cDate, dateTimeFormatter);

            LocalDate date = LocalDate.from(date1);

            months = date.until( date3, ChronoUnit.MONTHS );
            date = date.plusDays( months );

        }

        else if (!bDate.equals("null")){

//            LocalDate date1 = LocalDate.parse(pDate, dateTimeFormatter);
            LocalDate date2 = LocalDate.parse(bDate, dateTimeFormatter);
            LocalDate date3 = LocalDate.parse(cDate, dateTimeFormatter);

            LocalDate date = LocalDate.from(date2);

            months = date.until( date3, ChronoUnit.MONTHS );
            date = date.plusDays( months );
        }


        return root;
    }

    private void fetchPosts() {

//        String URL = "https://test.bloomingmoms.lk/api/mblog";
        String URL = getResources().getString(R.string.test_env) + "/api/mblog";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                if (roll.equals("admin")){

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

                            MonthlyPosts monthlyPosts = new MonthlyPosts(title, SiTitle, highlight, SiHighlight, description, SiDescription, image);
                            monthlyPostsList.add(monthlyPosts);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        MonthlyPostAdapter adapter = new MonthlyPostAdapter(MonthlyTabFragment.super.getContext(), monthlyPostsList);
                        recyclerView.setAdapter(adapter);
                    }
                }

                else {

                    for (int i = 0; i < months; i++) {
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

                            MonthlyPosts monthlyPosts = new MonthlyPosts(title, SiTitle, highlight, SiHighlight, description, SiDescription, image);
                            monthlyPostsList.add(monthlyPosts);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        MonthlyPostAdapter adapter = new MonthlyPostAdapter(MonthlyTabFragment.super.getContext(), monthlyPostsList);
                        recyclerView.setAdapter(adapter);
                    }
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(MonthlyTabFragment.super.getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonArrayRequest);
    }
}
