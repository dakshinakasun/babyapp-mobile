package com.example.babyapp.Fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.babyapp.Dashboard;
import com.example.babyapp.MainActivity;
import com.example.babyapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SignupTabFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    EditText username, email, password, conPassword, pdate, bdate;
    Spinner type;
    Button signup;

    final Calendar PdateCalendar = Calendar.getInstance();
    final Calendar BdateCalendar = Calendar.getInstance();
    private String roll = "user";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment, container, false);

        username = root.findViewById(R.id.username);
        email = root.findViewById(R.id.email);
        password = root.findViewById(R.id.password);
        conPassword = root.findViewById(R.id.con_password);
        type = root.findViewById(R.id.type_spinner);
        pdate = root.findViewById(R.id.pdate);
        bdate = root.findViewById(R.id.bdate);
        signup = root.findViewById(R.id.signupbtn);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.types_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        type.setAdapter(adapter);

        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0 || position == 1){
                    bdate.setVisibility(View.GONE);
                } else {
                    bdate.setVisibility(View.VISIBLE);
                }

                if (position == 2){
                    pdate.setVisibility(View.GONE);
                } else {
                    pdate.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Pregnant Edit Text Calender
        DatePickerDialog.OnDateSetListener pDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                PdateCalendar.set(Calendar.YEAR, year);
                PdateCalendar.set(Calendar.MONTH, monthOfYear);
                PdateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "yyyy-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                pdate.setText(sdf.format(PdateCalendar.getTime()));

            }

        };

        pdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(SignupTabFragment.super.getContext(), pDate, PdateCalendar
                        .get(Calendar.YEAR), PdateCalendar.get(Calendar.MONTH),
                        PdateCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //Baby was born Edit Text Calender
        DatePickerDialog.OnDateSetListener bDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                BdateCalendar.set(Calendar.YEAR, year);
                BdateCalendar.set(Calendar.MONTH, monthOfYear);
                BdateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "yyyy-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                bdate.setText(sdf.format(BdateCalendar.getTime()));

            }

        };

        bdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(SignupTabFragment.super.getContext(), bDate, BdateCalendar
                        .get(Calendar.YEAR), BdateCalendar.get(Calendar.MONTH),
                        BdateCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



        //sign up clickable event listener
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (username.getText().toString().isEmpty() || email.getText().toString().isEmpty() || password.getText().toString().isEmpty() || conPassword.getText().toString().isEmpty() || type.getSelectedItem().toString().isEmpty() || pdate.getText().toString().isEmpty() && bdate.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Required All Details", Toast.LENGTH_LONG).show();
                    return;
                }

                else if (!password.getText().toString().equals(conPassword.getText().toString())){
                    Toast.makeText(getContext(), "Password Doesn't Match", Toast.LENGTH_LONG).show();

                    password.setText("");
                    conPassword.setText("");

                    return;
                }

                postDataUsingVolley(username.getText().toString(), email.getText().toString(), password.getText().toString(), type.getSelectedItem().toString(), pdate.getText().toString(), bdate.getText().toString(), roll);
            }
        });

        return  root;
    }

    private void postDataUsingVolley(String mUsername, String mEmail, String mPassword, String mType, String mPdate, String mBdate, String mRoll) {

//        String url = "https://test.bloomingmoms.lk/api/register";
        String url = getResources().getString(R.string.test_env) + "/api/register";

        RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                username.setText("");
                email.setText("");
                password.setText("");
                conPassword.setText("");
//                type.setText("");
                pdate.setText("");
                bdate.setText("");

                Intent intent = new Intent(getContext(), MainActivity.class);
                Toast.makeText(getContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();
                startActivity(intent);


                try {

                    JSONObject respObj = new JSONObject(response);

                    String name = respObj.getString("name");
                    String email = respObj.getString("email");
                    String password = respObj.getString("password");
                    String type = respObj.getString("type");
                    String pdate = respObj.getString("pdate");
                    String bdate = respObj.getString("bdate");
                    String roll = respObj.getString("roll");



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();

                params.put("name", mUsername);
                params.put("email", mEmail);
                params.put("password", mPassword);
                params.put("type", mType);
                params.put("pdate", mPdate);
                params.put("bdate", mBdate);
                params.put("roll", mRoll);

                return params;
            }
        };
        queue.add(request);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
