package com.example.babyapp.ExtraClasses;

import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

public class User {

    public static User getUser(JSONObject jsonObject) throws JSONException{

        String name = jsonObject.getJSONObject("user").getString("name");
        String roll = jsonObject.getJSONObject("user").getString("roll");
        String email = jsonObject.getString("email");
        String pdate = jsonObject.getJSONObject("user").getString("pdate");
        String bdate = jsonObject.getJSONObject("user").getString("bdate");
        String token = jsonObject.getString("token");
        User user = new User(name, roll, email, pdate, bdate, token);

        return user;
    }

    private String name;
    private String roll;
    private String email;
    private String token;
    private String pdate;
    private String bdate;

    public User(String name, String roll, String email, String pdate, String bdate, String token) {
        this.name = name;
        this.roll = roll;
        this.email = email;
        this.pdate = pdate;
        this.bdate = bdate;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPdate() {
        return pdate;
    }

    public void setPdate(String pdate) {
        this.pdate = pdate;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    @Override
    public  boolean equals(Object obj){
        boolean result = false;

        if (obj != null && obj instanceof User){
            User that = (User) obj;
            if (this.email.equalsIgnoreCase(that.email)){
                result = true;
            }
        }
        return result;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", token='" + token + '\'' +
                ", pdate='" + pdate + '\'' +
                ", bdate='" + bdate + '\'' +
                '}';
    }
}
