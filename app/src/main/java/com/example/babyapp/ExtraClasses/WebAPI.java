package com.example.babyapp.ExtraClasses;

import android.app.Application;
import android.content.Intent;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.babyapp.Dashboard;
import com.example.babyapp.Fragment.LoginTabFragment;
import com.example.babyapp.Interfaces.API;
import com.example.babyapp.Interfaces.APIListener;
import com.example.babyapp.MainActivity;
import com.example.babyapp.R;

import org.json.JSONException;
import org.json.JSONObject;

public class WebAPI implements API {

//    public static final String BASE_URL = "https://test.bloomingmoms.lk/";

    private final Application mApplication;
    private RequestQueue mRequestQueue;

    public WebAPI(Application application){
        mApplication = application;
        mRequestQueue = Volley.newRequestQueue(application);
    }

    public void login(String email, String password, final APIListener listener){
        String url = mApplication.getResources().getString(R.string.test_env) + "/api/login";
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("email", email);
            jsonObject.put("password", password);

            Response.Listener<JSONObject> successListener = new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    try {
                        User user = User.getUser(response);
                        listener.onLogin(user);


                    } catch (JSONException e) {
                        Toast.makeText(mApplication, e.toString(), Toast.LENGTH_LONG).show();
                    }

                }
            };

            Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(mApplication, "Login Failed", Toast.LENGTH_LONG).show();
                }
            };

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, successListener, errorListener);
            mRequestQueue.add(request);

        } catch (JSONException e) {
            Toast.makeText(mApplication, "JSON Exception", Toast.LENGTH_LONG).show();
        }
    }
}
