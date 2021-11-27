package com.example.babyapp.Fragment;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.babyapp.Dashboard;
import com.example.babyapp.ExtraClasses.AbstractAPIListener;
import com.example.babyapp.ExtraClasses.Model;
import com.example.babyapp.ExtraClasses.User;
import com.example.babyapp.R;

import java.util.Objects;

public class LoginTabFragment extends Fragment {

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String pDate = "pDateKey";
    public static final String bDate = "bDateKey";
    public static final String Name = "nameKey";
    public static final String Roll = "rollKey";
    public static final String EmailK = "emailKey";
    public static final String PasswordK = "passwordKey";
    public static final String Check = "checkKey";

    private String textEmail, textPassword;
    private boolean checkOnOff;

    EditText email, password;
    Button login;
    TextView forgetPass;
    CheckBox checkBox;

    float v=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);

        email = root.findViewById(R.id.email);
        password = root.findViewById(R.id.password);
        login = root.findViewById(R.id.loginbtn);
        forgetPass = root.findViewById(R.id.forget_pass);
        checkBox = root.findViewById(R.id.checkBox);

        email.setAlpha(v);
        password.setAlpha(v);
        login.setAlpha(v);
        forgetPass.setAlpha(v);
        checkBox.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(400).start();
        forgetPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        checkBox.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(600).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        textEmail = sharedPreferences.getString(EmailK, null);
        textPassword = sharedPreferences.getString(PasswordK, null);
        checkOnOff = sharedPreferences.getBoolean(Check, false);

        if (textEmail != null && textPassword != null && checkOnOff){

            Intent intent = new Intent(getActivity(), Dashboard.class);
            startActivity(intent);

        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Required All Details", Toast.LENGTH_LONG).show();
                    return;
                }

                else{

                    String Email = email.getText().toString();
                    String Password = password.getText().toString();

                    final Model model = Model.getInstance((Application) getContext().getApplicationContext());
                    model.login(Email, Password, new AbstractAPIListener(){
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onLogin(User user){
                            model.setUser(user);

                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            editor.putString(EmailK, email.getText().toString());
                            editor.putString(PasswordK, password.getText().toString());
                            editor.putBoolean(Check, checkBox.isChecked());
//                            editor.apply();

                            editor.putString(Name, user.getName());
                            editor.putString(Roll, user.getRoll());
                            editor.putString(pDate, user.getPdate());
                            editor.putString(bDate, user.getBdate());
                            editor.apply();

                            Intent intent = new Intent(getActivity(), Dashboard.class);
                            startActivity(intent);
                            getActivity().finish();

                            Toast.makeText(getContext(),  user.getName() + " logged in Successfully", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        return  root;

    }
}
