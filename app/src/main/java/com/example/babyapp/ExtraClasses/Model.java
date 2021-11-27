package com.example.babyapp.ExtraClasses;

import android.app.Application;

import com.example.babyapp.Interfaces.API;
import com.example.babyapp.Interfaces.APIListener;

public class Model {

    private static Model sInstance = null;
    private User mUser;
    private final API mApi;

    public static Model getInstance(Application application) {

        if (sInstance == null) {
            sInstance = new Model(application);
        }
        return sInstance;
    }

    private final Application mApplication;

    private Model (Application application) {
        mApplication = application;
        mApi = new WebAPI(mApplication);
    }

    public Application getApplication() {
        return mApplication;
    }

    public void login(String email, String password, APIListener listener){

        mApi.login(email, password, listener);
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        this.mUser = user;
    }
}
